/*
 * Camel EndpointConfiguration generated by camel-api-component-maven-plugin
 */
package org.apache.camel.component.box;

import org.apache.camel.spi.ApiMethod;
import org.apache.camel.spi.ApiParam;
import org.apache.camel.spi.ApiParams;
import org.apache.camel.spi.Configurer;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;

/**
 * Camel endpoint configuration for {@link org.apache.camel.component.box.api.BoxFilesManager}.
 */
@ApiParams(apiName = "files", producerOnly = true,
           description = "Provides operations to manage Box files",
           apiMethods = {@ApiMethod(methodName = "checkUpload", description="Does a pre-verification before upload, to check if the filename already exists or if there is permission to upload", signatures={"void checkUpload(String fileName, String parentFolderId, Long size)"}), @ApiMethod(methodName = "copyFile", description="Copy file to destination folder while optionally giving it a new name", signatures={"com.box.sdk.BoxFile copyFile(String fileId, String destinationFolderId, String newName)"}), @ApiMethod(methodName = "createFileMetadata", description="Create metadata for file in either the global properties template or the specified template type", signatures={"com.box.sdk.Metadata createFileMetadata(String fileId, com.box.sdk.Metadata metadata, String typeName)"}), @ApiMethod(methodName = "createFileSharedLink", description="Create a shared link to file", signatures={"com.box.sdk.BoxSharedLink createFileSharedLink(String fileId, com.box.sdk.BoxSharedLink$Access access, java.util.Date unshareDate, com.box.sdk.BoxSharedLink$Permissions permissions)"}), @ApiMethod(methodName = "deleteFile", description="Delete the file", signatures={"void deleteFile(String fileId)"}), @ApiMethod(methodName = "deleteFileMetadata", description="Delete the file properties metadata", signatures={"void deleteFileMetadata(String fileId)"}), @ApiMethod(methodName = "deleteFileVersion", description="Delete a file version", signatures={"void deleteFileVersion(String fileId, Integer version)"}), @ApiMethod(methodName = "downloadFile", description="Download a file", signatures={"java.io.OutputStream downloadFile(String fileId, java.io.OutputStream output, Long rangeStart, Long rangeEnd, com.box.sdk.ProgressListener listener)"}), @ApiMethod(methodName = "downloadPreviousFileVersion", description="Download a previous version of file", signatures={"java.io.OutputStream downloadPreviousFileVersion(String fileId, Integer version, java.io.OutputStream output, com.box.sdk.ProgressListener listener)"}), @ApiMethod(methodName = "getDownloadURL", description="Get an expiring URL for downloading a file directly from Box", signatures={"java.net.URL getDownloadURL(String fileId)"}), @ApiMethod(methodName = "getFileInfo", description="Get file information", signatures={"com.box.sdk.BoxFile$Info getFileInfo(String fileId, String[] fields)"}), @ApiMethod(methodName = "getFileMetadata", description="Gets the file properties metadata", signatures={"com.box.sdk.Metadata getFileMetadata(String fileId, String typeName)"}), @ApiMethod(methodName = "getFilePreviewLink", description="Get an expiring URL for creating an embedded preview session", signatures={"java.net.URL getFilePreviewLink(String fileId)"}), @ApiMethod(methodName = "getFileVersions", description="Get any previous versions of file", signatures={"java.util.Collection<com.box.sdk.BoxFileVersion> getFileVersions(String fileId)"}), @ApiMethod(methodName = "moveFile", description="Move file to destination folder while optionally giving it a new name", signatures={"com.box.sdk.BoxFile moveFile(String fileId, String destinationFolderId, String newName)"}), @ApiMethod(methodName = "promoteFileVersion", description="Promote a previous version of file", signatures={"com.box.sdk.BoxFileVersion promoteFileVersion(String fileId, Integer version)"}), @ApiMethod(methodName = "renameFile", description="Rename file giving it the name newName", signatures={"com.box.sdk.BoxFile renameFile(String fileId, String newFileName)"}), @ApiMethod(methodName = "updateFileInfo", description="Update file information", signatures={"com.box.sdk.BoxFile updateFileInfo(String fileId, com.box.sdk.BoxFile$Info info)"}), @ApiMethod(methodName = "updateFileMetadata", description="Update the file properties metadata", signatures={"com.box.sdk.Metadata updateFileMetadata(String fileId, com.box.sdk.Metadata metadata)"}), @ApiMethod(methodName = "uploadFile", description="Upload a new file to parent folder", signatures={"com.box.sdk.BoxFile uploadFile(String parentFolderId, java.io.InputStream content, String fileName, java.util.Date created, java.util.Date modified, Long size, Boolean check, com.box.sdk.ProgressListener listener)"}), @ApiMethod(methodName = "uploadNewFileVersion", description="Upload a new version of file", signatures={"com.box.sdk.BoxFile uploadNewFileVersion(String fileId, java.io.InputStream fileContent, java.util.Date modified, Long fileSize, com.box.sdk.ProgressListener listener)"})}, aliases = {"uploadFile=upload", "downloadFile=download", "copyFile=copy", "moveFile=move", "renameFile=rename", "createFileSharedLink=link", "deleteFile=delete", "uploadNewFileVersion=uploadVersion", "promoteFileVersion=promoteVersion", "getFileVersions=versions", "downloadPreviousFileVersion=downloadVersion", "deleteFileVersion=deleteVersion", "getFileInfo=info", "updateFileInfo=updateInfo", "createFileMetadata=createMetadata", "getFileMetadata=metadata", "updateFileMetadata=updateMetadata", "deleteFileMetadata=deleteMetadata", "getDownloadUrl=url", "getPreviewLink=preview", "getFileThumbnail=thumbnail", "checkUpload=canUpload"})
@UriParams
@Configurer(extended = true)
public final class BoxFilesManagerEndpointConfiguration extends BoxConfiguration {
    @UriParam
    @ApiParam(optional = false, apiMethods = {@ApiMethod(methodName = "createFileSharedLink", description="The access level of the shared link")})
    private com.box.sdk.BoxSharedLink.Access access;
    @UriParam
    @ApiParam(optional = true, apiMethods = {@ApiMethod(methodName = "uploadFile", description="If the file name is already used, call the uploadNewVersion instead.")})
    private Boolean check;
    @UriParam
    @ApiParam(optional = false, apiMethods = {@ApiMethod(methodName = "uploadFile", description="A stream containing contents of the file to upload")})
    private java.io.InputStream content;
    @UriParam
    @ApiParam(optional = true, apiMethods = {@ApiMethod(methodName = "uploadFile", description="The content created date that will be given to the uploaded file")})
    private java.util.Date created;
    @UriParam
    @ApiParam(optional = false, apiMethods = {@ApiMethod(methodName = "copyFile", description="The id of the destination folder"), @ApiMethod(methodName = "moveFile", description="The id of the destination folder")})
    private String destinationFolderId;
    @UriParam
    @ApiParam(optional = true, apiMethods = {@ApiMethod(methodName = "getFileInfo", description="The information fields to retrieve; if null all information fields are retrieved.")})
    private String[] fields;
    @UriParam
    @ApiParam(optional = false, apiMethods = {@ApiMethod(methodName = "uploadNewFileVersion", description="A stream containing contents of the file to upload")})
    private java.io.InputStream fileContent;
    @UriParam
    @ApiParam(optional = false, apiMethods = {@ApiMethod(methodName = "copyFile", description="The id of file to copy"), @ApiMethod(methodName = "createFileMetadata", description="The id of the file to create metadata for"), @ApiMethod(methodName = "createFileSharedLink", description="The id of the file to create shared link on"), @ApiMethod(methodName = "deleteFile", description="The id of file to delete"), @ApiMethod(methodName = "deleteFileMetadata", description="The id of file to delete"), @ApiMethod(methodName = "deleteFileVersion", description="The id of file with version to delete"), @ApiMethod(methodName = "downloadFile", description="The id of file"), @ApiMethod(methodName = "downloadPreviousFileVersion", description="The id of file"), @ApiMethod(methodName = "getDownloadURL", description="The id of file"), @ApiMethod(methodName = "getFileInfo", description="The id of file"), @ApiMethod(methodName = "getFileMetadata", description="The id of the file to retrieve metadata for"), @ApiMethod(methodName = "getFilePreviewLink", description="The id of the file to get preview link on"), @ApiMethod(methodName = "getFileVersions", description="The id of file"), @ApiMethod(methodName = "moveFile", description="The id of file to move"), @ApiMethod(methodName = "promoteFileVersion", description="The id of file"), @ApiMethod(methodName = "renameFile", description="The id of file to rename"), @ApiMethod(methodName = "updateFileInfo", description="The id of file to update"), @ApiMethod(methodName = "updateFileMetadata", description="The id of file to delete"), @ApiMethod(methodName = "uploadNewFileVersion", description="The id of file")})
    private String fileId;
    @UriParam
    @ApiParam(optional = false, apiMethods = {@ApiMethod(methodName = "checkUpload", description="The name to give the uploaded file"), @ApiMethod(methodName = "uploadFile", description="The name to give the uploaded file")})
    private String fileName;
    @UriParam
    @ApiParam(optional = true, apiMethods = {@ApiMethod(methodName = "uploadNewFileVersion", description="The size of the file's content used for monitoring the upload's progress")})
    private Long fileSize;
    @UriParam
    @ApiParam(optional = false, apiMethods = {@ApiMethod(methodName = "updateFileInfo", description="The updated information")})
    private com.box.sdk.BoxFile.Info info;
    @UriParam
    @ApiParam(optional = true, apiMethods = {@ApiMethod(methodName = "downloadFile", description="A listener for monitoring the download's progress; if null the download's progress will not be monitored."), @ApiMethod(methodName = "downloadPreviousFileVersion", description="A listener for monitoring the download's progress; if null the download's progress will not be monitored."), @ApiMethod(methodName = "uploadFile", description="A listener for monitoring the upload's progress"), @ApiMethod(methodName = "uploadNewFileVersion", description="A listener for monitoring the upload's progress")})
    private com.box.sdk.ProgressListener listener;
    @UriParam
    @ApiParam(optional = false, apiMethods = {@ApiMethod(methodName = "createFileMetadata", description="The new metadata values"), @ApiMethod(methodName = "updateFileMetadata", description="The new metadata values")})
    private com.box.sdk.Metadata metadata;
    @UriParam
    @ApiParam(optional = true, apiMethods = {@ApiMethod(methodName = "uploadFile", description="The content modified date that will be given to the uploaded file"), @ApiMethod(methodName = "uploadNewFileVersion", description="The content modified date that will be given to the uploaded file")})
    private java.util.Date modified;
    @UriParam
    @ApiParam(optional = false, apiMethods = {@ApiMethod(methodName = "renameFile", description="The new name of file")})
    private String newFileName;
    @UriParam
    @ApiParam(optional = true, apiMethods = {@ApiMethod(methodName = "copyFile", description="The new name for copied file; if newName is null, the copied file has same name as the original."), @ApiMethod(methodName = "moveFile", description="The new name of moved file; if newName is null, the moved file has same name as the original.")})
    private String newName;
    @UriParam
    @ApiParam(optional = false, apiMethods = {@ApiMethod(methodName = "downloadFile", description="The stream to which the file contents will be written"), @ApiMethod(methodName = "downloadPreviousFileVersion", description="The stream to which the version contents will be written")})
    private java.io.OutputStream output;
    @UriParam
    @ApiParam(optional = false, apiMethods = {@ApiMethod(methodName = "checkUpload", description="The id of parent folder"), @ApiMethod(methodName = "uploadFile", description="The id of parent folder")})
    private String parentFolderId;
    @UriParam
    @ApiParam(optional = true, apiMethods = {@ApiMethod(methodName = "createFileSharedLink", description="The permissions of the created link; if permissions is null then the created shared link is created with default permissions.")})
    private com.box.sdk.BoxSharedLink.Permissions permissions;
    @UriParam
    @ApiParam(optional = true, apiMethods = {@ApiMethod(methodName = "downloadFile", description="The byte offset in file at which to stop the download; if null the entire contents of file will be downloaded.")})
    private Long rangeEnd;
    @UriParam
    @ApiParam(optional = true, apiMethods = {@ApiMethod(methodName = "downloadFile", description="The byte offset in file at which to start the download; if null the entire contents of file will be downloaded.")})
    private Long rangeStart;
    @UriParam
    @ApiParam(optional = true, apiMethods = {@ApiMethod(methodName = "checkUpload", description="The size of the file's content used for monitoring the upload's progress"), @ApiMethod(methodName = "uploadFile", description="The size of the file's content used for monitoring the upload's progress")})
    private Long size;
    @UriParam
    @ApiParam(optional = true, apiMethods = {@ApiMethod(methodName = "createFileMetadata", description="The metadata template type name; if null the global properties template type is used."), @ApiMethod(methodName = "getFileMetadata", description="The metadata template type name; if null the global properties template type is used.")})
    private String typeName;
    @UriParam
    @ApiParam(optional = true, apiMethods = {@ApiMethod(methodName = "createFileSharedLink", description="The date and time at which time the created shared link will expire; if unsharedDate is null then a non-expiring link is created.")})
    private java.util.Date unshareDate;
    @UriParam
    @ApiParam(optional = false, apiMethods = {@ApiMethod(methodName = "deleteFileVersion", description="The version of file to delete; initial version of file has value of 0, second version of file is 1 and so on."), @ApiMethod(methodName = "downloadPreviousFileVersion", description="The version of file to download; initial version of file has value of 0, second version of file is 1 and so on."), @ApiMethod(methodName = "promoteFileVersion", description="The version of file to promote; initial version of file has value of 0, second version of file is 1 and so on.")})
    private Integer version;

    public com.box.sdk.BoxSharedLink.Access getAccess() {
        return access;
    }

    public void setAccess(com.box.sdk.BoxSharedLink.Access access) {
        this.access = access;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public java.io.InputStream getContent() {
        return content;
    }

    public void setContent(java.io.InputStream content) {
        this.content = content;
    }

    public java.util.Date getCreated() {
        return created;
    }

    public void setCreated(java.util.Date created) {
        this.created = created;
    }

    public String getDestinationFolderId() {
        return destinationFolderId;
    }

    public void setDestinationFolderId(String destinationFolderId) {
        this.destinationFolderId = destinationFolderId;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public java.io.InputStream getFileContent() {
        return fileContent;
    }

    public void setFileContent(java.io.InputStream fileContent) {
        this.fileContent = fileContent;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public com.box.sdk.BoxFile.Info getInfo() {
        return info;
    }

    public void setInfo(com.box.sdk.BoxFile.Info info) {
        this.info = info;
    }

    public com.box.sdk.ProgressListener getListener() {
        return listener;
    }

    public void setListener(com.box.sdk.ProgressListener listener) {
        this.listener = listener;
    }

    public com.box.sdk.Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(com.box.sdk.Metadata metadata) {
        this.metadata = metadata;
    }

    public java.util.Date getModified() {
        return modified;
    }

    public void setModified(java.util.Date modified) {
        this.modified = modified;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public java.io.OutputStream getOutput() {
        return output;
    }

    public void setOutput(java.io.OutputStream output) {
        this.output = output;
    }

    public String getParentFolderId() {
        return parentFolderId;
    }

    public void setParentFolderId(String parentFolderId) {
        this.parentFolderId = parentFolderId;
    }

    public com.box.sdk.BoxSharedLink.Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(com.box.sdk.BoxSharedLink.Permissions permissions) {
        this.permissions = permissions;
    }

    public Long getRangeEnd() {
        return rangeEnd;
    }

    public void setRangeEnd(Long rangeEnd) {
        this.rangeEnd = rangeEnd;
    }

    public Long getRangeStart() {
        return rangeStart;
    }

    public void setRangeStart(Long rangeStart) {
        this.rangeStart = rangeStart;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public java.util.Date getUnshareDate() {
        return unshareDate;
    }

    public void setUnshareDate(java.util.Date unshareDate) {
        this.unshareDate = unshareDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
