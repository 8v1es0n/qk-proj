package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.entity.ClueTrackRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClueTrackRecordMapper extends BaseMapper<ClueTrackRecord> {
    /**
     * 根据id查询线索记录详情
     * @param id    线索记录id
     * @return      返回查询结果
     */
    @Select("select ctr.*, u.name as assign_name from clue_track_record ctr left join sys_user u " +
            "on ctr.user_id = u.id where ctr.clue_id = #{id} order by create_time desc")
    List<ClueTrackRecord> queryCTR(Integer id);
}
