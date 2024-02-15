package io.kimnaparknam.kkulkkeok.post;

import io.kimnaparknam.kkulkkeok.common.ResponseDto;
import io.kimnaparknam.kkulkkeok.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //게시글 리스트 조회
    @GetMapping("/p")
    public ResponseEntity<ResponseDto<List<PostResponseDto>>> getPostList() {
        List<PostResponseDto> dto = postService.getPostList();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("", HttpStatus.OK.value(), dto));
    }


    //게시글 작성
    @PostMapping("")
    public ResponseEntity<ResponseDto<Void>> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            postService.createPost(postRequestDto, userDetails.getUser());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body(new ResponseDto<>("게시글 작성에 실패했습니다.", HttpStatus.BAD_REQUEST.value(), null));
        }
        return ResponseEntity.status((HttpStatus.OK)).body(new ResponseDto<>("게시글 작성에 성공했습니다.", HttpStatus.OK.value(), null));
    }

    //게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<ResponseDto<Void>> modifyPost(@PathVariable Long postId, @RequestBody PostRequestDto modifyPostRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            postService.modifyPost(postId, modifyPostRequestDto, userDetails.getUser());
        } catch (IllegalArgumentException | AuthorizationServiceException e) {
            return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body(new ResponseDto<>("게시글 수정 실패 : " + e.getMessage(), HttpStatus.BAD_REQUEST.value(), null));
        }
        return ResponseEntity.status((HttpStatus.OK)).body(new ResponseDto<>("게시글 수정에 성공했습니다.", HttpStatus.OK.value(), null));
    }

    //특정 유저의 모든 게시글 조회
    @GetMapping("/p/users/{nickname}")
    public ResponseEntity<ResponseDto<List<PostResponseDto>>> getUserPostList(@PathVariable(name = "nickname") String nickname){
        List<PostResponseDto> postResponseDtoList;
        try{
            postResponseDtoList = postService.getUserPostList(nickname);
        }catch(IllegalArgumentException e){
            return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body(new ResponseDto<>("게시글 조회 실패 : " + e.getMessage(), HttpStatus.BAD_REQUEST.value(), null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("특정 유저 포스팅 목록 조회 완료",HttpStatus.OK.value(), postResponseDtoList));
    }

    //특정 게시글 조회
    @GetMapping("/p/{postId}")
    public ResponseEntity<ResponseDto<PostResponseDto>> getPost(@PathVariable(name = "postId") Long postId){
        PostResponseDto responseDto;
        try {
            responseDto = postService.getPost(postId);
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto<>("게시글 조회 실패 : " + e.getMessage(), HttpStatus.BAD_REQUEST.value(), null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("게시글 조회 성공", HttpStatus.OK.value(), responseDto));
    }
    //특정 게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseDto<Void>> deletePost(@PathVariable(name = "postId") Long postId,@AuthenticationPrincipal UserDetailsImpl userDetails){
        try {
            postService.deletePost(postId, userDetails.getUser());
        }catch (AuthorizationServiceException | IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto<>("게시글 삭제 실패 : " + e.getMessage(), HttpStatus.BAD_REQUEST.value(), null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>("게시글 삭제 성공", HttpStatus.OK.value(), null));
    }
}
