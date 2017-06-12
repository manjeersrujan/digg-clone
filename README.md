This is sample clone for Digg.com with simple topics, in-memory storage(No persistence) using a Priority Queue(Min heap) of fixed size.


Assumptions: 

1) No user logins and registrations
2) Topics with same title and content is allowed by same user or different user
3) Upvotes and Downvotes can submitted by any user to any topic any number of times.
4) UI fancy designs, validations etc are not needed. It is simple UI and might have some validation and rendering issues.  

Design: 
1) REST services for every operation and UI just uses to render data.
2) REST services implemented in java, springboot
3) Proper exception handling framework created. Sending a new error response in generic format is just a config change
4) Popular topics are maintained in a Priority Queue(Min heap). So, updating PQ is of time complexity O(LogN) and to get all popular topics O(20) which is constant.
5) PQs size is configurable.
6) PQs are maintained for every kind of vote (Upvote, Downvote etc). Just adding a new Enum takes care of everything and no further code changes needed.
7) NO UNIT TESTS WERE WRITTEN. But, code is very much testable. 
8) All operations are thread safe. Used synchronization where ever it is required and provided comments at every place explaining why it is used.(See TopicsDao.java)


