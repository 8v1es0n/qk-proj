package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dto.CluePoolDTO;
import com.itheima.dto.ClueQueryDTO;
import com.itheima.entity.Clue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClueMapper extends BaseMapper<Clue> {
    /**
     * 根据条件进行分页查询线索
     * @param cluePage  分页参数
     * @param clueQueryDTO      分页条件
     * @return  返回查询结果
     */
    Page<Clue> listClues(Page<Clue> cluePage, ClueQueryDTO clueQueryDTO);

    /**
     * 根据条件进行分页查询线索池
     * @param page  分页参数
     * @param cluePoolDTO      分页条件
     * @return  返回查询结果
     */
    Page<Clue> getPoolClues(Page<Clue> page, CluePoolDTO cluePoolDTO);
}
