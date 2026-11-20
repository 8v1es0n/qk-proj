package com.itheima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.common.PageResult;
import com.itheima.dto.CluePoolDTO;
import com.itheima.dto.ClueQueryDTO;
import com.itheima.dto.MarkFalseClueDTO;
import com.itheima.entity.Clue;

public interface ClueService extends IService<Clue> {
    /**
     * 列表查询
     * @param clueQueryDTO 查询参数
     * @return 查询结果
     */
    PageResult<Clue> listClues(ClueQueryDTO clueQueryDTO);


    /**
     * 分配线索
     *
     * @param clueId 线索ID
     * @param userId 用户ID
     */

    void assignClue(Integer clueId, Integer userId);

    /**
     * 根据id查询线索详情
     * @param id    线索id
     * @return      返回查询结果
     */
    Clue getClueInfoById(Integer id);

    /**
     * 跟进线索信息
     * @param clue  线索信息
     * @parom token 当前用户凭证
     */
    void trackClue(Clue clue, String token);

    /**
     * 将线索转为商机
     * @param id 线索ID
     */
    void convertToBusiness(Integer id);

    /**
     *将没有意向的线索标记为伪线索，后续不再跟进
     * @param id    线索id
     * @param mfc   伪线索信息
     */
    void markFalseClue(Integer id, MarkFalseClueDTO mfc);

    /**
     * 查询线索池列表
     * @param cluePoolDTO   查询参数
     * @return      返回查询到的结果
     */
    PageResult<Clue> getPoolClues(CluePoolDTO cluePoolDTO);
}
