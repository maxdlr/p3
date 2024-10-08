# Routes to set 

## Auth 
[x] - Register 
> route: ``/auth/register`` 
> method: POST
> body: {name, email, password} 

[x] - Login
> route: ``/auth/login``
> method: POST > body: {email, password} 

## Messages 
[x] - Add 
> route: ``/messages``
> method: POST > body: {message} 

## Rentals 
[x] - Browse 
> route: ``/rentals``
> method: GET 

[x] - Read 
> route: ``/rental/:id``
> method: GET 

[x] - Add 
> route: ``/rentals``
> method: POST
> body: {...rentalParams} 

[x] - Edit 
> route: ``/rentals/:id``
> method: PUT
> body: {...editedRentalParams} 

## User 
[x] - Read 
> route: ``/users/:id``
> method: GET
