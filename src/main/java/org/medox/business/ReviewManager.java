package org.medox.business;

import org.medox.dao.ReviewDao;
import org.medox.model.Review;

public class ReviewManager {
    public static Review getReview(Long id){
        ReviewDao dao=new ReviewDao();
        Review review=dao.getReview(id);
        dao.close();
        return review;
    }
    public static Long saveReview(Review review) {
        ReviewDao dao = new ReviewDao();
        dao.saveReview(review);
        dao.close();
        return review.getId();
    }
}
