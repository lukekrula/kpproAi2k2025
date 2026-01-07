package cz.uhk.kppro.model;

import jakarta.persistence.*;

@Entity
public class ParticipationRequestAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "request_id")
    private ParticipationRequest request;

    private String filename;
    private String fileType;
    private Long fileSize;
    private String storagePath;

    public Long getId() { return id; }

    public ParticipationRequest getRequest() { return request; }
    public void setRequest(ParticipationRequest request) { this.request = request; }

    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }

    public String getStoragePath() { return storagePath; }
    public void setStoragePath(String storagePath) { this.storagePath = storagePath; }
}
