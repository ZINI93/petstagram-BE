package com.zini93.petstagram.domain.like.service;


import com.zini93.petstagram.domain.like.dto.LikeResponseDto;
import com.zini93.petstagram.domain.like.entity.Like;
import com.zini93.petstagram.domain.like.exception.UserUuidAndLikeUuidNotFoundException;
import com.zini93.petstagram.domain.like.repository.LikeRepository;
import com.zini93.petstagram.domain.post.entity.Post;
import com.zini93.petstagram.domain.post.exception.PostUuidNotFoundException;
import com.zini93.petstagram.domain.post.repository.PostRepository;
import com.zini93.petstagram.domain.user.entity.User;
import com.zini93.petstagram.domain.user.exception.UserUuidNotFoundException;
import com.zini93.petstagram.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class LikeServiceImpl implements LikeService{

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private Like createLike(User user, Post post){
        return Like.builder().user(user).post(post).build();
    }

    @Override @Transactional
    public LikeResponseDto createLikeWithValidate(String userUuid, String postUuid) {

        User user = findUserByUserUuidInUser(userUuid);
        Post post = findPostByPostUuidInPost(postUuid);

        Like like = createLike(user, post);
        Like savedLike = likeRepository.save(like);

        return savedLike.toResponse();
    }

    @Override @Transactional
    public void deleteLike(String userUuid, String likeUuid) {

        Like like = findLikeByUserUuidAndLikeInLike(userUuid, likeUuid);

        likeRepository.delete(like);
    }

    @Override
    public LikeResponseDto getLikeCountByPostUuid(String productUuid) {
        Post post = findPostByPostUuidInPost(productUuid);
        Like countLike = likeRepository.countByPost(post);

        return countLike.toResponse();
    }

    private Post findPostByPostUuidInPost(String postUuid) {
        return postRepository.findByPostUuid(postUuid)
                .orElseThrow(() -> new PostUuidNotFoundException("Post not found"));
    }

    private User findUserByUserUuidInUser(String userUuid) {
        return userRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new UserUuidNotFoundException("User not found"));
    }

    private Like findLikeByUserUuidAndLikeInLike(String userUuid, String likeUuid) {
        return likeRepository.findByUserUserUuidAndLikeUuid(userUuid, likeUuid)
                .orElseThrow(() -> new UserUuidAndLikeUuidNotFoundException("Like not found"));
    }
}
