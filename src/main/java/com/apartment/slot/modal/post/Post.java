package com.apartment.slot.modal.post;

import com.apartment.slot.modal.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQueries({
        @NamedQuery(
                name = "Post.getPostByPostId",
                query = "SELECT p FROM Post p " +
                        "LEFT JOIN FETCH p.childList p1 " +
                        "WHERE p.postId = : postId"
        ),
        @NamedQuery(
                name = "Post.allParent",
                query = "SELECT p FROM Post p " +
                        "LEFT JOIN FETCH p.childList pl1 " +
                        "WHERE p.parent IS NULL"
        ),
})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    private String title;
    private String message;
    private String authorEmail;
    private String authorName;
    @Column(name = "post_uuid", unique = true, nullable = false)
    private String postId;
    private long dateOfCreate;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    private Post parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Post> childList = new ArrayList<>();

    public void addChildPost(Post post) {
        this.childList.add(post);
        post.setParent(this);
    }

    public void removeChildPost(Post post) {
        this.childList.remove(post);
        post.setParent(null);
    }

}
