package com.example.mvc.controller.get

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController // REST API Controller 동작
@RequestMapping("/api") // http://localhost:8080/api
class GetApiController {

    @GetMapping(path = ["/hello", "abcd"]) // GET http://localhost:8080/api/hello , abcd
    fun hello(): String {
        return "hello kotlin"
    }

    @RequestMapping(method = [RequestMethod.GET], path = ["/request-mapping"]) // 옛날 방식
    fun requestMapping(): String {
        return "request-mapping"
    }

    @GetMapping("/get-mapping/path-variable/{name}/{age}") // GET http://localhost:8080/api/get-mapping/path-variable/steve
    fun pathVariable(@PathVariable name: String, @PathVariable age: Int): String {
        println("$name , $age")
        return name + age
    }

    @GetMapping("/get-mapping/path-variable2/{name}/{age}") // GET http://localhost:8080/api/get-mapping/path-variable/steve
    fun pathVariable2(
        @PathVariable(value = "name") _name: String,
        @PathVariable(name = "age") age: Int
    ): String { // 경로변수로 받는거랑 밑에서 사용하는 변수명이랑 다르게 하기

        val name = "kotlin"

        println("$_name , $age")
        return _name + age
    }

    // http://localhost:8080/api/get-mapping/query-param?name=steve&age=20
    @GetMapping("/get-mapping/query-param")
    fun queryParam(
        @RequestParam(name = "name") name: String, // name, value 동일
        @RequestParam(value = "age") age: Int, // 변수명 다르게 받기
    ): String {
        println("$name, $age")
        return name + age
    }

    // name, age, address, email VO로 한번에 받기
    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObj(userRequest: UserRequest): UserRequest { // 객체로 받을때는 "-" 사용 불가, @RequestParam(name = "name")으로 받아야함
        println(userRequest)
        return userRequest
    }

    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map: Map<String, Any>): Map<String, Any> { // 맵일때는 "-" 사용가능
        println(map)
        val name = map.get("name")
        return map
    }

}