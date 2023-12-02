
# IAM (Identity and Access Management)

## Personas

In our system, we recognize three major personas, each with distinct access mechanisms:

1. **Theater Employee**: Employees with access to management functionalities, like adding a Theater Screen, adding Showtimes, Configuring seating capacity.
2. **Member**: Registered users with access to enhanced features.
3. **Guest User**: Non-registered users with limited access, primarily to the landing page.

### User Type Differentiation

For backend operations, user types are differentiated using the `USER_TYPE` field. This field helps in identifying the persona and providing the appropriate level of access.

## Authentication

### Overview

We employ Cookie-based Authentication to manage user sessions and access control.

### Custom Annotation - `@LoginRequired`

To streamline the enablement or disablement of authentication checks, we have implemented a custom annotation: `@LoginRequired`.

#### Working

When applied to a controller method, `@LoginRequired` activates an Interceptor that checks for the presence of a valid authentication cookie. If the cookie is present, the request proceeds; otherwise, access is denied.

#### Usage

To apply authentication to a particular controller method, simply annotate it with `@LoginRequired`. The annotation processor not only validates the session but also retrieves the current user associated with that session.

[LoginRequired Interceptor](https://github.com/gopinathsjsu/teamproject-scrumandcoke/blob/main/backend/src/main/java/com/scrumandcoke/movietheaterclub/interceptor/LoginRequiredInterceptor.java)

## Authorization

Authorization in our system is closely tied to authentication. It is enabled only on APIs that also have authentication checks in place. The authorization process is handled through another custom annotation: `@UserTypesAllowed`.

### `@UserTypesAllowed` Annotation

The `@UserTypesAllowed` annotation is used to specify which user types are allowed to access a particular controller method. This annotation is a prerequisite for effective authorization.

#### Example Usage

```java
@PutMapping("/{id}")
@LoginRequired
@UserTypesAllowed({UserType.THEATER_EMPLOYEE})
public ResponseEntity<TheaterScreenDto> updateTheaterScreen(@PathVariable int id, @RequestBody TheaterScreenDto theaterScreenDto) {
    try {
        TheaterScreenDto updatedDto = theaterScreenService.updateTheaterScreen(id, theaterScreenDto);
        return ResponseEntity.ok(updatedDto);
    } catch (GlobalException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
```

In this example, the `updateTheaterScreen` method is annotated with `@LoginRequired` to ensure that the user is authenticated and `@UserTypesAllowed` to restrict access to only those users who are identified as `THEATER_EMPLOYEE`.

[UserTypesAllowed Interceptor](https://github.com/gopinathsjsu/teamproject-scrumandcoke/blob/main/backend/src/main/java/com/scrumandcoke/movietheaterclub/interceptor/UserTypesAllowedInterceptor.java)
