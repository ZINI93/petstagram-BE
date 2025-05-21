package com.zini93.petstagram.domain.post.service;


import com.zini93.petstagram.domain.post.dto.PostRequestDto;
import com.zini93.petstagram.domain.post.dto.PostResponseDto;
import com.zini93.petstagram.domain.post.dto.PostUpdateDto;
import com.zini93.petstagram.domain.post.entity.Post;
import com.zini93.petstagram.domain.post.exception.UserUuidAndPostUuidNotFoundException;
import com.zini93.petstagram.domain.post.repository.PostRepository;
import com.zini93.petstagram.domain.user.entity.User;
import com.zini93.petstagram.domain.user.exception.UserUuidNotFoundException;
import com.zini93.petstagram.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService{


    private final PostRepository postRepository;
    private final UserRepository userRepository;


    private Post createPost(User user, PostRequestDto requestDto) {

        return Post.builder()
                .user(user)
                .postImageUrl(requestDto.getPostImageUrl())
                .content(requestDto.getContent())
                .build();
    }

    @Override
    public PostResponseDto createPostWithValidate(String userUuid, PostRequestDto requestDto) {

        User user = userRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new UserUuidNotFoundException("User not found"));

        Post post = createPost(user, requestDto);
        Post savedPost = postRepository.save(post);

        return savedPost.toResponse();
    }

    @Override
    public PostResponseDto getPostInfo(String userUuid, String postUuid) {

        Post post = findPostByUserUuidAndPostUuidInPostUuid(userUuid, postUuid);
        return post.toResponse();
    }

    @Override
    public Page<PostResponseDto> getAllPost(String userUuid, String postUuid, Pageable pageable) {
        return null;
    }

    private Post updatePost(String userUuid, String postUuid , PostUpdateDto updateDto){

        Post post = findPostByUserUuidAndPostUuidInPostUuid(userUuid, postUuid);
        post.updatePost(updateDto.getPostImageUrl(),updateDto.getContent());
        return post;
    }

    @Override
    public PostResponseDto updatePostWithValidate(String userUuid, String postUuid, PostUpdateDto updateDto) {

        Post post = updatePost(userUuid, postUuid, updateDto);
        return post.toResponse();
    }

    @Override
    public void deletePost(String userUuid, String postUuid) {

        Post post = findPostByUserUuidAndPostUuidInPostUuid(userUuid, postUuid);
        postRepository.delete(post);
    }

    private Post findPostByUserUuidAndPostUuidInPostUuid(String userUuid, String postUuid) {
        return postRepository.findByUserUserUuidAndPostUuid(userUuid, postUuid)
                .orElseThrow(() -> new UserUuidAndPostUuidNotFoundException("Post Not found"));
    }
}
