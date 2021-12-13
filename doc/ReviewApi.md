# ReviewApi

All URIs are relative to *https://52market.shop*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteReview**](ReviewApi.md#deleteReview) | **DELETE** /api/reviews/{id} |
[**editReview**](ReviewApi.md#editReview) | **PUT** /api/reviews/{id} |
[**getReviews**](ReviewApi.md#getReviews) | **GET** /api/reviews/{id} |
[**writeReview**](ReviewApi.md#writeReview) | **POST** /api/reviews |

<a name="deleteReview"></a>

# **deleteReview**

> Long deleteReview(id)



리뷰 삭제하기

### Example

```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ReviewApi;


ReviewApi apiInstance = new ReviewApi();
Long id = 789L; // Long | 
try {
    Long result = apiInstance.deleteReview(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ReviewApi#deleteReview");
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

<a name="editReview"></a>

# **editReview**

> Review editReview(body, id)



리뷰 변경하기

### Example

```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ReviewApi;


ReviewApi apiInstance = new ReviewApi();
Review body = new Review(); // Review | 
Long id = 789L; // Long | 
try {
    Review result = apiInstance.editReview(body, id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ReviewApi#editReview");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**body** | [**Review**](Review.md)|  |
**id** | **Long**|  |

### Return type

[**Review**](Review.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: */*

<a name="getReviews"></a>

# **getReviews**

> List&lt;Review&gt; getReviews(id)



리뷰 id로 가져오기

### Example

```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ReviewApi;


ReviewApi apiInstance = new ReviewApi();
Long id = 789L; // Long | 
try {
    List<Review> result = apiInstance.getReviews(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ReviewApi#getReviews");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**id** | **Long**|  |

### Return type

[**List&lt;Review&gt;**](Review.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*

<a name="writeReview"></a>

# **writeReview**

> Review writeReview(body)



리뷰 저장하기

### Example

```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ReviewApi;


ReviewApi apiInstance = new ReviewApi();
Review body = new Review(); // Review | 
try {
    Review result = apiInstance.writeReview(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ReviewApi#writeReview");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**body** | [**Review**](Review.md)|  |

### Return type

[**Review**](Review.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: */*

