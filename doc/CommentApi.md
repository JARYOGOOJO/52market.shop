# CommentApi

All URIs are relative to *https://52market.shop*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getComments**](CommentApi.md#getComments) | **GET** /api/comments/{id} |
[**removeComment**](CommentApi.md#removeComment) | **DELETE** /api/comment/{id} |
[**writeComment**](CommentApi.md#writeComment) | **POST** /api/comments |

<a name="getComments"></a>

# **getComments**

> List&lt;Comment&gt; getComments(id)



댓글 id로 가져오기

### Example

```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.CommentApi;


CommentApi apiInstance = new CommentApi();
Long id = 789L; // Long | 
try {
    List<Comment> result = apiInstance.getComments(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CommentApi#getComments");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**id** | **Long**|  |

### Return type

[**List&lt;Comment&gt;**](Comment.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*

<a name="removeComment"></a>

# **removeComment**

> Long removeComment(id)



댓글 삭제

### Example

```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.CommentApi;


CommentApi apiInstance = new CommentApi();
Long id = 789L; // Long | 
try {
    Long result = apiInstance.removeComment(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CommentApi#removeComment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**id** | **Long**|  |

### Return type

**Long**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*

<a name="writeComment"></a>

# **writeComment**

> Comment writeComment(body)



댓글 작성

### Example

```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.CommentApi;


CommentApi apiInstance = new CommentApi();
Comment body = new Comment(); // Comment | 
try {
    Comment result = apiInstance.writeComment(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CommentApi#writeComment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**body** | [**Comment**](Comment.md)|  |

### Return type

[**Comment**](Comment.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: */*

