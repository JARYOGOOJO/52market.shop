# ArticleApi

All URIs are relative to *https://52market.shop*

Method | HTTP request | Description
------------- | ------------- | -------------
[**editArticle**](ArticleApi.md#editArticle) | **PUT** /api/article |
[**getAllArticles**](ArticleApi.md#getAllArticles) | **GET** /api/articles |
[**getArticles**](ArticleApi.md#getArticles) | **GET** /api/articles/{query} |
[**removeArticle**](ArticleApi.md#removeArticle) | **DELETE** /api/article/{id} |
[**writeArticle**](ArticleApi.md#writeArticle) | **POST** /api/article |

<a name="editArticle"></a>

# **editArticle**

> Article editArticle(body)



게시글 편집

### Example

```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ArticleApi;


ArticleApi apiInstance=new ArticleApi();
        Article body=new Article(); // Article | 
        try{
        Article result=apiInstance.editArticle(body);
        System.out.println(result);
        }catch(ApiException e){
        System.err.println("Exception when calling ArticleApi#editArticle");
        e.printStackTrace();
        }
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**body** | [**Article**](Article.md)|  |

### Return type

[**Article**](Article.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: */*

<a name="getAllArticles"></a>

# **getAllArticles**

> List&lt;Article&gt; getAllArticles()



모든 게시글 가져오기

### Example

```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ArticleApi;


ArticleApi apiInstance=new ArticleApi();
        try{
        List<Article> result=apiInstance.getAllArticles();
        System.out.println(result);
        }catch(ApiException e){
        System.err.println("Exception when calling ArticleApi#getAllArticles");
        e.printStackTrace();
        }
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**List&lt;Article&gt;**](Article.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*

<a name="getArticles"></a>

# **getArticles**

> List&lt;Article&gt; getArticles(query)



게시글 검색

### Example

```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ArticleApi;


ArticleApi apiInstance=new ArticleApi();
        String query="query_example"; // String | 
        try{
        List<Article> result=apiInstance.getArticles(query);
        System.out.println(result);
        }catch(ApiException e){
        System.err.println("Exception when calling ArticleApi#getArticles");
        e.printStackTrace();
        }
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**query** | **String**|  |

### Return type

[**List&lt;Article&gt;**](Article.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*

<a name="removeArticle"></a>

# **removeArticle**

> Long removeArticle(id)



게시글 삭제

### Example

```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ArticleApi;


ArticleApi apiInstance=new ArticleApi();
        Long id=789L; // Long | 
        try{
        Long result=apiInstance.removeArticle(id);
        System.out.println(result);
        }catch(ApiException e){
        System.err.println("Exception when calling ArticleApi#removeArticle");
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

<a name="writeArticle"></a>

# **writeArticle**

> Article writeArticle(body)



게시글 작성

### Example

```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ArticleApi;


ArticleApi apiInstance=new ArticleApi();
        ApiArticleBody body=new ApiArticleBody(); // ApiArticleBody | 
        try{
        Article result=apiInstance.writeArticle(body);
        System.out.println(result);
        }catch(ApiException e){
        System.err.println("Exception when calling ArticleApi#writeArticle");
        e.printStackTrace();
        }
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**body** | [**ApiArticleBody**](ApiArticleBody.md)|  | [optional]

### Return type

[**Article**](Article.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: */*

