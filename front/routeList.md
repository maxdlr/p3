# Routes to set

- Register
> route: ``/auth/register``
> method: POST
> body: {name, email, password}

- Login
> route: ``/auth/login``
> method: POST
> body: {email, password}

- Messages
> route: ``/messages``
> method: POST
> body: {message}

- Rentals
Browse
> route: ``/rentals``
> method: GET

Read
> route: ``/rental/:id``
> method: GET

Add
> route: ``/rentals``
> method: POST
> body: {...rentalParams}

Edit
> route: ``/rentals/:id``
> method: PUT
> body: {...editedRentalParams}

- User
> route: ``/users/:id``
> method: GET
