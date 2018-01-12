package com.springnetty.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.springnetty.web.common.ResponseCode;
import com.springnetty.web.common.ResponseResult;
import com.springnetty.web.controller.model.TestModel;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api")
public class TestController {

    @ApiOperation(value = "Get请求")
    @RequestMapping(value = "/getRequest", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody Object getRequest(HttpServletRequest request) {
        ResponseResult<String> response = new ResponseResult<>(ResponseCode.Success, "hello world");
        return response;
    }

    @ApiOperation(value = "Post Json字符串")
    @RequestMapping(value = "/postJson", method = RequestMethod.POST)
    public @ResponseBody Object postJson(@RequestBody TestModel model, HttpServletRequest request) {
        ResponseResult<TestModel> response = new ResponseResult<>(ResponseCode.Success, model);
        return response;
    }

    @ApiOperation(value = "上传文件")
    @RequestMapping(value = "/portMultipartFile", method = RequestMethod.POST)
    public @ResponseBody Object uploadImg(@RequestParam(required = true) String message, MultipartFile file) {
        ResponseResult<String> response = new ResponseResult<>(ResponseCode.Success, message);
        return response;
    }

    @ApiOperation(value = "获取路径参数")
    @RequestMapping(value = "/post/{pathVariable}", method = RequestMethod.POST)
    public @ResponseBody Object postPathVariable(@PathVariable String pathVariable, HttpServletRequest req) throws IOException {
        ResponseResult<String> response = new ResponseResult<>(ResponseCode.Success, pathVariable);
        return response;
    }

    @ApiOperation(value = "POST参数")
    @RequestMapping(value = "/postData", method = RequestMethod.POST)
    public @ResponseBody Object postData(@RequestParam(required = true) String message) {
        ResponseResult<String> response = new ResponseResult<>(ResponseCode.Success, message);
        return response;
    }

    @ApiOperation(value = "异常处理实例")
    @RequestMapping(value = "/handleExceptionDemo", method = RequestMethod.POST)
    public void handleExceptionDemo(@RequestParam(required = true) String message) {
        System.out.println(1 / 0);
    }
}
