# User

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**createdAt** | [**OffsetDateTime**](OffsetDateTime.md) |  |  [optional]
**modifiedAt** | [**OffsetDateTime**](OffsetDateTime.md) |  |  [optional]
**id** | **Long** |  |  [optional]
**name** | **String** |  |  [optional]
**socialId** | **Long** |  |  [optional]
**email** | **String** |  |  [optional]
**picture** | **String** |  |  [optional]
**password** | **String** |  |  [optional]
**phoneNumber** | **String** |  |  [optional]
**latitude** | **Double** |  |  [optional]
**longitude** | **Double** |  |  [optional]
**star** | **Double** |  |  [optional]
**role** | [**RoleEnum**](#RoleEnum) |  |  [optional]

<a name="RoleEnum"></a>

## Enum: RoleEnum

Name | Value
---- | -----
GUEST | &quot;GUEST&quot;
USER | &quot;USER&quot;
ADMIN | &quot;ADMIN&quot;
