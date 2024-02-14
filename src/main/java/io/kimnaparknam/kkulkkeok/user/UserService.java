package io.kimnaparknam.kkulkkeok.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequestDto signupRequestDto) {
        String encodedPassword = passwordEncoder.encode(signupRequestDto.getPassword());
        //중복 확인
        Optional<User> checkNickname = userRepository.findByNickname(signupRequestDto.getNickname());
        if(checkNickname.isPresent()){
            throw new IllegalArgumentException("해당 닉네임은 이미 존재합니다.");
        }
        Optional<User> checkEmail = userRepository.findByEmail(signupRequestDto.getEmail());
        if(checkEmail.isPresent()){
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }

        User user = new User(signupRequestDto, encodedPassword);
        userRepository.save(user);
    }
}
