package com.itheima.controller;


import com.itheima.aspect.anno.LogOperation;
import com.itheima.common.PageResult;
import com.itheima.common.Result;
import com.itheima.dto.CluePoolDTO;
import com.itheima.dto.ClueQueryDTO;
import com.itheima.dto.MarkFalseClueDTO;
import com.itheima.entity.Clue;
import com.itheima.service.ClueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/clues")
public class ClueController {

    @Autowired
    private ClueService clueService;

    /**
     * 新增线索
     * @param clue 线索
     * @return  返回结果
     */
    @PostMapping
    public Result addClue(@RequestBody Clue clue) {
        log.info("新增线索: {}", clue);
        clue.setStatus(1);  //1:待分配
        clueService.saveOrUpdate(clue);
        return Result.success();
    }

    /**
     * 列表查询
     * @param clueQueryDTO 查询参数
     * @return 查询结果
     */
    @GetMapping
    public Result listClues(ClueQueryDTO clueQueryDTO) {
        log.info("查询参数: {}", clueQueryDTO);
        PageResult<Clue> pageResult = clueService.listClues(clueQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * 分配线索
     * @param clueId 线索ID
     * @param userId 用户ID
     * @return Result
     */
    @PutMapping("/assign/{clueId}/{userId}")
    public Result assignClue(@PathVariable Integer clueId, @PathVariable Integer userId) {
        log.info("分配线索: 线索ID={}, 用户ID={}", clueId, userId);
        // 保存更新后的线索信息
        clueService.assignClue(clueId,userId);
        return Result.success();
    }

    /**
     * 根据id查询线索详情
     * @param id    线索id
     * @return      返回查询结果
     */
    @GetMapping("/{id}")
    public Result getClueInfoById(@PathVariable Integer id){
        Clue clue = clueService.getClueInfoById(id);
        return Result.success(clue);
    }

    /**
     * 跟进线索信息
     * @param clue  线索信息
     * @return  返回结果
     */
    @PutMapping
    public Result trackClue(@RequestBody Clue clue,
                            @RequestHeader(value = "token" ,required = false) String token){
        clueService.trackClue(clue, token);
        return Result.success();
    }

    /**
     *将没有意向的线索标记为伪线索，后续不再跟进
     * @param id    线索id
     * @param mfc   伪线索信息
     * @return      返回结果
     */
    @LogOperation
    @PutMapping("/false/{id}")
    public Result markFalseClue(@PathVariable Integer id, @RequestBody MarkFalseClueDTO mfc){
        clueService.markFalseClue(id, mfc);
        return Result.success();
    }

    /**
     * 将线索转为商机
     * @param id 线索ID
     * @return 操作结果
     */
    @LogOperation
    @PutMapping("/toBusiness/{id}")
    public Result convertToBusiness(@PathVariable Integer id) {
        log.info("将线索转为商机, id: {}", id);
        clueService.convertToBusiness(id);
        return Result.success();
    }

    /**
     * 查询线索池列表
     * @param cluePoolDTO   查询参数
     * @return      返回查询到的结果
     */
    @GetMapping("/pool")
    public Result getPoolClues(CluePoolDTO cluePoolDTO) {
        log.info("查询线索池列表,  {}", cluePoolDTO);
        PageResult<Clue> pageResult = clueService.getPoolClues(cluePoolDTO);
        return Result.success(pageResult);
    }
}
