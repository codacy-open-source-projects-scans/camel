/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.dsl.jbang.core.commands.k;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.camel.dsl.jbang.core.commands.CamelJBangMain;
import org.apache.camel.dsl.jbang.core.commands.bind.TemplateProvider;
import org.apache.camel.util.ObjectHelper;
import org.apache.camel.v1.Pipe;
import org.apache.camel.v1.integrationspec.Traits;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "bind",
         description = "Bind Kubernetes resources such as Kamelets in a new integration pipe connecting a source and a sink",
         sortOptions = false)
public class Bind extends KubeBaseCommand {

    private final org.apache.camel.dsl.jbang.core.commands.bind.Bind delegate;

    @CommandLine.Parameters(description = "The name of the Pipe resource created on the cluster.", arity = "1",
                            paramLabel = "<name>", parameterConsumer = NameConsumer.class)
    Path pipeName; // Defined only for code completion; the field never used
    String name;

    @CommandLine.Option(names = { "--source" },
                        description = "Source (from) such as a Kamelet or Camel endpoint uri that provides data.",
                        required = true)
    String source;

    @CommandLine.Option(names = { "--step" },
                        description = "Add optional 1-n steps to the pipe processing. Each step represents a reference to a Kamelet of type action.")
    String[] steps;

    @CommandLine.Option(names = { "--sink" },
                        description = "Sink (to) such as a Kamelet or Camel endpoint uri where data should be sent to.",
                        required = true)
    String sink;

    @CommandLine.Option(names = { "--error-handler" },
                        description = "Add error handler (none|log|sink:<endpoint>). Sink endpoints are expected in the format \"[[apigroup/]version:]kind:[namespace/]name\", plain Camel URIs or Kamelet name.")
    String errorHandler;

    @CommandLine.Option(names = { "--property" },
                        description = "Add a pipe property in the form of [source|sink|error-handler|step-<n>].<key>=<value> where <n> is the step number starting from 1.",
                        arity = "0")
    String[] properties;

    @CommandLine.Option(names = { "--output" },
                        description = "Output format generated by this command (supports: file, yaml or json).")
    String output;

    @CommandLine.Option(names = { "--operator-id" },
                        defaultValue = "camel-k",
                        description = "Operator id selected to manage this integration.")
    String operatorId = "camel-k";

    @CommandLine.Option(names = { "--connect" },
                        description = "A Service that the integration should bind to, specified as [[apigroup/]version:]kind:[namespace/]name.")
    String[] connects;

    @CommandLine.Option(names = { "--annotation" },
                        description = "Add an annotation to the integration. Use name values pairs like \"--annotation my.company=hello\".")
    String[] annotations;

    @CommandLine.Option(names = { "--traits" },
                        description = "Add a label to the integration. Use name values pairs like \"--label my.company=hello\".")
    String[] traits;

    @CommandLine.Option(names = { "--wait" }, description = "Wait for the pipe to become ready.")
    boolean wait;

    @CommandLine.Option(names = { "--logs" }, description = "Print logs after pipe has been started.")
    boolean logs;

    public Bind(CamelJBangMain main) {
        super(main);
        delegate = new org.apache.camel.dsl.jbang.core.commands.bind.Bind(
                main, new TemplateProvider() {
                    @Override
                    public InputStream getPipeTemplate() {
                        return Bind.class.getClassLoader()
                                .getResourceAsStream("templates/pipe.yaml.tmpl");
                    }
                });
    }

    @Override
    public Integer doCall() throws Exception {
        // Operator id must be set
        if (ObjectHelper.isEmpty(operatorId)) {
            printer().println("Operator id must be set");
            return -1;
        }

        delegate.setFile(name);
        delegate.setSource(source);
        delegate.setSink(sink);
        delegate.setSteps(steps);
        delegate.setErrorHandler(errorHandler);
        delegate.setProperties(properties);

        String pipe = delegate.constructPipe();

        if (pipe.isEmpty()) {
            // Error in delegate exit now
            printer().println("Failed to construct Pipe resource");
            return -1;
        }

        // --operator-id={id} is a syntax sugar for '--annotation camel.apache.org/operator.id={id}'
        if (annotations == null) {
            annotations = new String[] { "%s=%s".formatted(KubeCommand.OPERATOR_ID_LABEL, operatorId) };
        } else {
            annotations = Arrays.copyOf(annotations, annotations.length + 1);
            annotations[annotations.length - 1] = "%s=%s".formatted(KubeCommand.OPERATOR_ID_LABEL, operatorId);
        }

        String annotationsContext = "";
        if (annotations != null) {
            StringBuilder sb = new StringBuilder("  annotations:\n");

            for (String annotation : annotations) {
                String[] keyValue = annotation.split("=", 2);
                if (keyValue.length != 2) {
                    printer().printf(
                            "annotation '%s' does not follow format <key>=<value>%n",
                            annotation);
                    continue;
                }

                sb.append("    ").append(keyValue[0]).append(": ").append(keyValue[1]).append("\n");
            }

            annotationsContext = sb.toString();
        }

        pipe = pipe.replaceFirst("\\{\\{ \\.Annotations }}\n", annotationsContext);

        String integrationSpec = "";
        Traits traitsSpec = null;
        if (traits != null && traits.length > 0) {
            traitsSpec = TraitHelper.parseTraits(traits);
        }

        if (connects != null) {
            if (traitsSpec == null) {
                traitsSpec = new Traits();
            }
            TraitHelper.configureConnects(traitsSpec, connects);
        }

        if (traitsSpec != null) {
            String traitYaml = KubernetesHelper.dumpYaml(traitsSpec);
            traitYaml = traitYaml.replaceAll("\n", "\n        ");
            integrationSpec = "  integration:\n    spec:\n      traits:\n        %s\n".formatted(traitYaml.trim());
        }

        pipe = pipe.replaceFirst("\\{\\{ \\.IntegrationSpec }}\n", integrationSpec);

        if (output != null) {
            delegate.setOutput(output);
            return delegate.dumpPipe(pipe);
        }

        Pipe pipeResource = KubernetesHelper.yaml().loadAs(pipe, Pipe.class);
        final AtomicBoolean updated = new AtomicBoolean(false);
        client(Pipe.class).resource(pipeResource).createOr(it -> {
            updated.set(true);
            return it.update();
        });

        if (updated.get()) {
            printer().printf("Pipe %s updated%n", pipeResource.getMetadata().getName());
        } else {
            printer().printf("Pipe %s created%n", pipeResource.getMetadata().getName());
        }

        if (wait || logs) {
            client(Pipe.class).withName(pipeResource.getMetadata().getName())
                    .waitUntilCondition(it -> "Running".equals(it.getStatus().getPhase()), 10, TimeUnit.MINUTES);
        }

        if (logs) {
            new IntegrationLogs(getMain()).watchLogs(pipeResource.getMetadata().getName());
        }

        return 0;
    }

    static class NameConsumer extends ParameterConsumer<Bind> {
        @Override
        protected void doConsumeParameters(Stack<String> args, Bind cmd) {
            cmd.name = args.pop();
        }
    }

}
