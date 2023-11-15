package com.example.demo.src.Member;


import lombok.RequiredArgsConstructor;
import com.example.demo.common.BaseException;
import com.example.demo.common.BaseResponse;
import com.example.demo.src.Member.model.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


import static com.example.demo.common.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/users")
public class MemberController {


    private final MemberService memberService;


    /**
     * 회원가입 API
     * [POST] /app/users
     * @return BaseResponse<PostMemberRes>
     */
    // Body
    @ResponseBody
    @PostMapping("")
    public BaseResponse<PostMemberRes> createUser(@RequestBody PostMemberReq postMemberReq) {
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!
        if(postMemberReq.getEmail() == null){
            return new BaseResponse<>(POST_USERS_EMPTY_EMAIL);
        }
        //이메일 정규표현
        if(!isRegexEmail(postMemberReq.getEmail())){
            return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
        }
        try{
            PostMemberRes postMemberRes = memberService.createUser(postMemberReq);
            return new BaseResponse<>(postMemberRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 회원 조회 API
     * [GET] /app/users
     * 모든 회원 조회 API
     * [GET] /app/users
     * @return BaseResponse<GetMembersRes>
     */
    //Query String
    @ResponseBody
    @Secured("ROLE_ADMIN")
    @GetMapping("") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<GetMembersRes> getAllUsers() {
        try{
            GetMembersRes getMembersRes = memberService.getUsers();
            return new BaseResponse<>(getMembersRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 회원 1명 조회 API
     * [GET] /app/users/:userId
     * @return BaseResponse<GetMemberRes>
     */
    // Path-variable
//    @ResponseBody
//    @GetMapping("/{userId}") // (GET) 127.0.0.1:9000/app/users/:userId
//    public BaseResponse<GetMemberRes> getUser(@PathVariable("userId") int userId) {
//        // Get Users
//        try{
//            GetMemberRes getUserRes = userService.getUser(userId);
//            return new BaseResponse<>(getUserRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//
//    }
//
//
//
//    /**
//     * 유저정보변경 API
//     * [PATCH] /app/users/:userId
//     * @return BaseResponse<String>
//     */
//    @ResponseBody
//    @PatchMapping("/{userId}")
//    public BaseResponse<String> modifyUserName(@PathVariable("userId") int userId, @RequestBody Member user){
//        try {
//            PatchMemberReq patchUserReq = new PatchMemberReq(userId,user.getName());
//            userService.modifyUserName(patchUserReq);
//
//            String result = "";
//        return new BaseResponse<>(result);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }
//
//    /**
//     * 유저정보삭제 API
//     * [DELETE] /app/users/:userId
//     * @return BaseResponse<String>
//     */
//    @ResponseBody
//    @DeleteMapping("/{userId}")
//    public BaseResponse<String> deleteUser(@PathVariable("userId") int userId){
//        try {
//            userService.deleteUser(userId);
//
//            String result = "";
//            return new BaseResponse<>(result);
//        } catch (BaseException exception) {
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }


}
