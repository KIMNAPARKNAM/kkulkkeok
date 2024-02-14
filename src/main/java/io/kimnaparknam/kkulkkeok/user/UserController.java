package io.kimnaparknam.kkulkkeok.user;

import io.kimnaparknam.kkulkkeok.common.ResponseDto;
import io.kimnaparknam.kkulkkeok.security.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j //logging
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<Void>> signup(@RequestBody @Valid SignupRequestDto signupRequestDto, BindingResult bindingResult){
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            for(FieldError fieldError : fieldErrors){
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
        }
        try{
            userService.signup(signupRequestDto);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new ResponseDto<>("중복된 username", HttpStatus.BAD_REQUEST.value(),null));
        }

        return ResponseEntity.status(HttpStatus.CREATED.value()).body(new ResponseDto<>("회원가입 성공", HttpStatus.CREATED.value(),null));
    }

    //회원가입 -> 이메일 중복 확인
    @GetMapping("/v/email/{email}")
    public ResponseEntity<ResponseDto<Void>> checkEmail(@PathVariable(name = "email") @Valid String email) {
        try {
            userService.checkLoginEmail(email);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseDto<>(e.getMessage(), 400,null));
        }
        return ResponseEntity.ok().body(new ResponseDto<>("사용 가능한 이메일 입니다.", 200,null));
    }

    //회원가입 -> 닉네임 중복확인
    @GetMapping("/v/nickname/{nickname}")
    public ResponseEntity<ResponseDto<Void>> checkNickname(@PathVariable(name = "nickname") String nickname) {
        try {
            userService.checkNickname(nickname);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseDto<>(e.getMessage(), 400,null));
        }
        return ResponseEntity.ok().body(new ResponseDto<>("사용 가능한 닉네임 입니다.", 200,null));
    }


    @GetMapping("/logout")
    public ResponseEntity<ResponseDto<Void>> logout(HttpServletResponse response){
        response.setHeader(JwtUtil.AUTHORIZATION_HEADER,"");
        return ResponseEntity.ok().body(new ResponseDto<>("로그아웃 되었습니다.",200,null));
    }
}
