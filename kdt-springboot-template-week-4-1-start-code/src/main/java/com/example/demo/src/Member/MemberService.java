package com.example.demo.src.Member;



import com.example.demo.common.BaseException;
import com.example.demo.src.Member.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.common.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@RequiredArgsConstructor
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public PostMemberRes createUser(PostMemberReq postMemberReq) throws BaseException {
        //중복
        if(checkEmail(postMemberReq.getEmail())){
            throw new BaseException(POST_USERS_EXISTS_EMAIL);
        }
        try{
            Member member = postMemberReq.toEntity();
            memberRepository.save(member);
            return new PostMemberRes(member.getId());
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

//    public void modifyUserName(PatchMemberReq patchUserReq) throws BaseException {
//        try{
//            Member user = userRepository.findById()
//            int result = userRepository.save()
//            if(result == 0){
//                throw new BaseException(MODIFY_FAIL_USERNAME);
//            }
//        } catch(Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    public void deleteUser(int userId) throws BaseException {
//        try{
//            int result = userDao.deleteUser(userId);
//            if(result == 0){
//                throw new BaseException(DELETE_FAIL_USERNAME);
//            }
//        } catch(Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
    public GetMembersRes getUsers() throws BaseException{
        try{
            GetMembersRes getMembersRes = GetMembersRes.listof(memberRepository.findAll());
            return getMembersRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
//
//    public List<GetMemberRes> getUsersByEmail(String email) throws BaseException{
//        try{
//            List<GetMemberRes> getUsersRes = userDao.getUsersByEmail(email);
//            return getUsersRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//
//    public GetMemberRes getUser(int userId) throws BaseException {
//        try {
//            GetMemberRes getUserRes = userDao.getUser(userId);
//            return getUserRes;
//        } catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
    public boolean checkEmail(String email) throws BaseException{
        try{
            return memberRepository.existsByEmail(email);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
