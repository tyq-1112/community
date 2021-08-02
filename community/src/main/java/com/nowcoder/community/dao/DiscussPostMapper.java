package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussPostMapper {

    // 参数userId，用于“个人主页”开发

    //分页查询帖子  offset：每一页起始行行号 ，limit：每一页最多显示的数据
    List<DiscussPost> selectDiscussPosts(int userId , int offset , int limit);

    //一共帖子数量(未拉黑)
    // @Param 用于给参数起别名 ，如果只有一个参数，并且在动态SQL使用，则必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);
}
