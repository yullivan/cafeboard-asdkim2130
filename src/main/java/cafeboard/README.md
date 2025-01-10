# 게시판(board)
## 게시판 생성
### method : PostMapping
### /boards
### http://localhost:8080/boards

## 게시판 목록 조회
### method : GetMapping
### /boards/{boardId}}
### http://localhost:8080/boards/1

```
{
    "boardName" : 게시판이름,
    [
        post1,
        post2,
        post3
    ]
}
```

## 게시판 수정
### method : PutMapping
### /boards/{boardId}
### http://localhost:8080/boards/1


## 게시판 삭제
### method : DeleteMapping
### /boards/{boardId}
### http://localhost:8080/boards/1


# 게시글(post)
## 게시글 생성
### method : PostMapping
### /posts
### http://localhost8080/posts

## 게시글 목록조회
### method : GetMapping
### /posts
### http://localhost8080/posts
````
PostResponse
[
    {    
        "postId" : 1
        "postTitle" : "게시글 제목"
        "commentsCount" : 1
    }
]
````

## 게시글 상세 조회
### method : GetMapping
### /posts/{postId}
### http://localhost8080/posts/1
````
DetailPostResponse
{
    "postId" : 1
    "postTitle" : "게시글 제목"
    "contents" : "게시글 내용"
    "comment" :  
        [
            comment1,
            comment2,
            comment3
        ]
}
````

## 게시글 수정
### method : PutMapping
### /posts/{postId}
### http://localhost8080:posts/1

## 게시글 삭제
### method : DeleteMapping
### posts/{postId}
### http://localhost8080:posts/1

# 댓글
## 댓글 생성
### method : PostMapping
### /comments
### http://localhost8080:comments
````
{
CommentRequest
    "contents" : "댓글 내용"
}
````

