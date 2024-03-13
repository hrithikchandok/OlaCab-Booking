# OlaCab Booking Application
It is built to support all the functionality related to managing a taxi booking system. 
We will discuss each aspect of this project in detail for enhanced understanding.
The CAB Booking System project is designed in Spring Boot and Hibernate along with the source code.


![image](https://github.com/hrithikchandok/OlaCab-Booking/assets/95309424/a8013c93-cf1e-4793-baca-33b32c436539)
Sure, let's incorporate these additional points into the features section:

### Features:

1. **Cab Booking by Type and Location:**
   - Users can book a cab according to their preferred cab type (Auto, Mini cab, Sedan Cab) and specify the pickup and drop-off locations (e.g., City1 to City2).

2. **Automatic Driver Assignment:**
   - Drivers for the requested cab type are automatically assigned based on price considerations. 
   - In the Indian market, where pricing is crucial, drivers with the most competitive rates are prioritized.
   - In case of price parity among multiple drivers, allocation is based on driver ratings to ensure quality service.

3. **Dynamic Pricing Calculation:**
   - Prices for cab rides are dynamically calculated based on the distance between the pickup and drop-off locations entered by the user.

4. **Trip Amount Calculation:**
   - The total trip amount is calculated using two factors:
     - Distance traveled between the pickup and drop-off locations.
     - The driver's rate per kilometer charge.

5. **Cab Confirmation Email:**
   - Upon successful allocation of a driver, a confirmation email containing essential trip details is sent to the user. Details may include driver information, cab type, pickup time, and estimated fare.

6. **OTP Verification:**
   - A 4-digit OTP (One-Time Password) is generated and sent to the customer for verification of the allocated driver, ensuring security and trust in the service.

7. **Payment Time Window:**
   - Once a customer schedules a trip and a driver is allocated, a 5-10 minute window is provided for the customer to complete the payment.
   - If the payment is not made within the specified time frame, the trip is automatically canceled, and the allocated driver becomes available for other trips.

8. **Data Pruning Process:**
   - Implemented automatic removal of data from the database where payment is not completed within a specified time period.
   - After every 5 minutes, the system checks for trips where payment has not been completed for more than 10 minutes.
   - Such data is then archived in a trip archive table, making the driver available for other trips and optimizing database performance.
9. **Optimized API Performance with Redis Cache:**
   - Redis cache is employed to store frequently accessed data such as driver details and pre-calculated distances between commonly traveled cities.
   - By leveraging Redis cache, database interaction is minimized, resulting in enhanced API performance and reduced latency.
# Functions of Admin, Customer, Driver
## Through admin we can : 
1. Can Log in/Log out of the system.<br>
2. Admin can View/Edit/Delete taxis into the system.<br>
3. Admin can View/Confirm/Cancel booking done by the User.<br>
4. Can check payments done by User.<br>
5. Can check the availability of cabs and drivers.<br>
6. Can manage driver staff.<br>
7. Can change the charge.<br>
8. Can change password.<br>

## Through Customer we can:
1. Can Log in/Log out of the system.
2. Can Manage “My profile”.<br>
3. Can search for cab.<br>
4. Users can Book/Cancel cabs.<br>
5. Can change password.<br>

## Through Driver we can:
1. Add a driver in the system.
2. Update driver details.
3. Delete a driver from the system.
4. Get the list of all drivers.
5. Get the list of all best available(rated>=4.5) drivers.
