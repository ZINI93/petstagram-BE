package com.zini93.petstagram.domain.follow.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zini93.petstagram.domain.follow.dto.FollowResponseDto;
import com.zini93.petstagram.domain.follow.dto.QFollowResponseDto;
import com.zini93.petstagram.domain.follow.entity.QFollow;
import com.zini93.petstagram.domain.user.entity.QUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.zini93.petstagram.domain.follow.entity.QFollow.*;
import static com.zini93.petstagram.domain.user.entity.QUser.*;

@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<FollowResponseDto> followerList(String userUuid, String followerUuid, String username, Pageable pageable) {

        BooleanExpression predicate = buildPredicate(userUuid, followerUuid, username);

        // 데이터 조회
        List<FollowResponseDto> content = queryFactory
                .select(new QFollowResponseDto(
                        follow.followUuid,
                        follow.follower.userUuid
                ))
                .from(follow)
                .leftJoin(follow.follower, user)
                .where(predicate)
                .orderBy(follow.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(follow.count())
                .from(follow)
                .where(predicate);


        return PageableExecutionUtils.getPage(content,pageable,countQuery::fetchOne);
    }

    @Override
    public Page<FollowResponseDto> followingList(String userUuid, String followingUuid, String nickname, Pageable pageable) {

        BooleanExpression Predicate = followingBuildPredicate(userUuid, followingUuid, nickname);

        List<FollowResponseDto> content = queryFactory
                .select(new QFollowResponseDto(
                        follow.followUuid,
                        follow.following.userUuid
                )).from(follow)
                .leftJoin(follow.following, user)
                .where(Predicate)
                .orderBy(follow.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(follow.count())
                .from(follow)
                .where(Predicate);

        return PageableExecutionUtils.getPage(content,pageable,countQuery::fetchOne);
    }


    private BooleanExpression followingBuildPredicate(String userUuid, String followingUuid, String nickName){
        BooleanExpression followingPredicate = Expressions.TRUE.isTrue();
        if (userUuid != null) followingPredicate = followingPredicate.and(filterByUser(userUuid));
        if (followingUuid != null) followingPredicate = followingPredicate.and(filterByFollowing(followingUuid));
        if (nickName != null) followingPredicate = followingPredicate.and(filterByNickname(nickName));
        return followingPredicate;
    }



    private BooleanExpression buildPredicate(String userUuid, String followerUuid, String nickName
    ) {

        BooleanExpression predicate = Expressions.TRUE.isTrue();
        if (userUuid != null) predicate = predicate.and(filterByUser(userUuid));
        if (followerUuid != null) predicate = predicate.and(filterByFollower(followerUuid));
        if (nickName != null) predicate = predicate.and(filterByNickname(nickName));
        return predicate;
    }


    private BooleanExpression filterByUser(String userUuid) {
        if (userUuid == null || userUuid.isEmpty()) {
            return null;
        }
        return follow.follower.userUuid.eq(userUuid);
    }

    private BooleanExpression filterByFollower(String followerUuid) {
        if (followerUuid == null || followerUuid.isEmpty()) {
            return null;
        }
        return follow.follower.userUuid.eq(followerUuid);
    }

    private BooleanExpression filterByFollowing(String followingUuid) {
        if (followingUuid == null || followingUuid.isEmpty()) {
            return null;
        }
        return follow.following.userUuid.eq(followingUuid);
    }

    private BooleanExpression filterByNickname(String nickName) {
        return containCond(nickName, follow.follower.nickName);
    }

    private static BooleanExpression containCond(String value, StringPath field) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return field.containsIgnoreCase(value);
    }

}