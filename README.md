# AndroidService
Sample code of bound service and unbound service.


# Getting Started
* You may clone or download and build the project.
* Two activities in the project are not related to each other.
* If you want to change main activity that is shown first, you should change manifest file.


# Contents
![services](./img/services.jpg)

## Unbound Service
* Ubound service is started from startService() method and excuted in background infintely,
  even if the original activity is gone.
* However, the service cannot return any values to caller.
  
## Bound Service (= Service Binding)
 * To make service binding object, you should make onBind() method, which is a callback method.
 * onBind() returns IBinder, which plays interface role between 'service' and 'client'.
 * Service Binding can communicate with caller.
 * However, It doesn't work always in background.
 
 # Source
 * all the contents of the project are from http://bitsoul.tistory.com/

