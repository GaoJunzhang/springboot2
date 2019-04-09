package com.seeyoo.visit.controller;


import com.seeyoo.visit.model.Constant;
import com.seeyoo.visit.result.ResultVO;
import com.seeyoo.visit.service.ConstantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/constant")
@Api(value = "Constant", tags = {"Constant"})
public class ConstantController {
    @Autowired
    private ConstantService constantService;

    @RequestMapping(value = "/getConstant",method = RequestMethod.GET)
    @ApiOperation(value = "统计配置")
    public ResultVO<Constant> getConstant(){
        return ResultVO.getSuccess("success",constantService.selectAll());
    }

    @RequestMapping(value = "/postConstant",method = RequestMethod.POST)
    @ApiOperation(value = "更新配置")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "id", dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "vaildAdb", value = "vaildAdb", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "vaildeBdb", value = "vaildeBdb", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "vaildPadb", value = "vaildPadb", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "vaildPbdb", value = "vaildPbdb", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "aLevel", value = "aLevel", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "bLevel", value = "bLevel", dataType = "String"),
    })
    public ResultVO postConstant(Constant constant) {
        if (constant==null){
            return ResultVO.getFailed("constant data is null");
        }
        try {
            constantService.updateAll(constant);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.getFailed("execption",e);
        }
        return ResultVO.getSuccess("success",null);
    }
}
