package io.kimnaparknam.kkulkkeok.user;

import io.kimnaparknam.kkulkkeok.common.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
