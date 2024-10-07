# Routes to set

[x] - Register
> route: ``/auth/register``
> method: POST
> body: {name, email, password}

[x] - Login
> route: ``/auth/login``
> method: POST
> body: {email, password}

[x] - Messages
> route: ``/messages``
> method: POST
> body: {message}

[x] - Rentals
Browse
> route: ``/rentals``
> method: GET

[x] - Read
> route: ``/rental/:id``
> method: GET

[x] - Add
> route: ``/rentals``
> method: POST
> body: {...rentalParams}

[ ] - Edit
> route: ``/rentals/:id``
> method: PUT
> body: {...editedRentalParams}

[x] - User
> route: ``/users/:id``
> method: GET
