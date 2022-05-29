package model;

public class ReviewsDTO {
    private int postId;
    private String userId;
    private String title;
    private String content;
    private int stars;
    private String image;

    public ReviewsDTO(int postId, String userId, String title, String content, Integer stars, String image) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.stars = stars;
        this.image = image;
    }

    public ReviewsDTO(int postId) {
        this.postId = postId;
    }

    public ReviewsDTO(String image) {
        this.image = image;
    }
    public int getPostId() {
        return postId;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getStars() {
        return stars;
    }

    public String getImage() {
        return image;
    }

    public ReviewsDTO() {

    }

}