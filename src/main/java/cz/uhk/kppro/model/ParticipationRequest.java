package cz.uhk.kppro.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ParticipationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- RELATIONSHIPS -------------------------------------------------------

    // The program this request is about
    @ManyToOne(optional = false)
    @JoinColumn(name = "program_id")
    private Program program;

    // The community that created the request
    @ManyToOne(optional = false)
    @JoinColumn(name = "community_id")
    private Community community;

    // The partner who is being asked to participate
    @ManyToOne(optional = false)
    @JoinColumn(name = "partner_id")
    private Partner partner;

    // The user who created the request (usually community manager)
    @ManyToOne(optional = false)
    @JoinColumn(name = "creator_user_id")
    private User creator;



    private String name;
    private String description;
    private Double amountRequested;
    private String sharedWith;
    private String manager;



    @Enumerated(EnumType.STRING)
    private ParticipationRequestStatus status = ParticipationRequestStatus.PENDING;

    @Column(columnDefinition = "TEXT")
    private String proposalText;



    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParticipationRequestAttachment> attachments = new ArrayList<>();



    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }



    public Long getId() { return id; }

    public Program getProgram() { return program; }
    public void setProgram(Program program) { this.program = program; }

    public Community getCommunity() { return community; }
    public void setCommunity(Community community) { this.community = community; }

    public Partner getPartner() { return partner; }
    public void setPartner(Partner partner) { this.partner = partner; }

    public User getCreator() { return creator; }
    public void setCreator(User creator) { this.creator = creator; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getAmountRequested() { return amountRequested; }
    public void setAmountRequested(Double amountRequested) { this.amountRequested = amountRequested; }

    public String getSharedWith() { return sharedWith; }
    public void setSharedWith(String sharedWith) { this.sharedWith = sharedWith; }

    public String getManager() { return manager; }
    public void setManager(String manager) { this.manager = manager; }

    public ParticipationRequestStatus getStatus() { return status; }
    public void setStatus(ParticipationRequestStatus status) { this.status = status; }

    public String getProposalText() { return proposalText; }
    public void setProposalText(String proposalText) { this.proposalText = proposalText; }

    public List<ParticipationRequestAttachment> getAttachments() { return attachments; }
    public void setAttachments(List<ParticipationRequestAttachment> attachments) { this.attachments = attachments; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
