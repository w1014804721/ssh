package me.hagen.ssh.dao;

import me.hagen.ssh.domain.WorkCommentPicture;

import org.springframework.stereotype.Repository;

@Repository("workCommentPictureDao")   //这个值和service层里的那个对象名字一致
public class WorkCommentPictureDao extends BaseDao<WorkCommentPicture> {

}
