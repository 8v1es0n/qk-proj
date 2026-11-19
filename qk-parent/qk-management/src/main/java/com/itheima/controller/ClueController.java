package com.itheima.controller;


import com.itheima.common.PageResult;
import com.itheima.common.Result;
import com.itheima.dto.ClueQueryDTO;
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
}
