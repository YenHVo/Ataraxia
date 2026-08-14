7/27/2026 - 8/10/2026:
- Set up frontend and backend
- Design the initial database (Entities, repositories, services, and controllers)

8/11/2026 - 8/18/2026:
- Test backend with postman, ensuring that relationships work properly
- Begin implementing business logic (Room Availability/ Reservation Pricing / Payment Tracking / Inventory Management)

FIX:
- Ensure that password is not shown in Postman once User Authentication is implemented. (Hashing)
- Fix UPDATE/PUT method in all controller/service classes. When updating, it is possible to update unique variables to each other, thus causing an issue. Furthermore, any relationships will turn into null (Not a big issue as GET will retrieve the necessary values). 
- InventoryItem + InventoryTransaction: Both have quantity value. Is this repetitive?
- Check Out Date can't be later than check in date.
- Implement helper methods (aka. calculate reservation pricing automatically, use dtos, etc.)

For the future:
- Add validation (ex. quantity can't be negative)
- User Authentication
- Security