package com.apartment.slot.modal.apartment.review;

import com.b2c.prototype.modal.entity.user.UserDetails;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "review_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    @Column(name = "commentId", unique = true, nullable = false)
    private String commentId;
    private String title;
    private String message;
    private long dateOfCreate;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private UserDetails userDetails;
    @ManyToOne
    @JoinColumn(name = "comment_id")
    @ToString.Exclude
    private ReviewComment parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<ReviewComment> childList = new ArrayList<>();

    public void addChildComment(ReviewComment comment) {
        this.childList.add(comment);
        comment.setParent(this);
    }

    public void removeChildComment(ReviewComment comment) {
        this.childList.remove(comment);
        comment.setParent(null);
    }
}
