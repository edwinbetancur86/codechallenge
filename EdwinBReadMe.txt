My Thought Process For Completing implementation:

I left off at writing the today hourly forecast for
the first recyclerview that handles today.

I was going to continue the implementation by
using the rest call to successfully retrieve the
data from the Weather Service and make sure that
the card items would display across the card view 
board. After this, once that is done, I was going 
to check to see if the right data was display correctly
on to the board. During this process I would make sure
to set the right drawable resource id for the proper
condition for the hour. I would then proceed to 
color code the image condition with it's proper temp color.
I would color a resource value xml to perform this. 

Moving further, I would do the same process for tomorrow's
card (going in order). But this time, I would use RxJava
observables in conjunction with my rest call with retrofit
to emit my weather model data. I would first create the 
observables globally then set them within the onCreate.

After all this is done, I would proceed to writing code
for the next activity which handles the settings. 
Once I set the settings adapter, I would allow the user
to touch the option by settings the view in the adapter
as a clickable onClick item. This would then open the dialog
ask the user to enter a zipcode. And the same situation would
happen for the metrics except with different logic that goes
with that flow. 

Then I would proceed to brushing up and polishing the material 
design with the layouts according to specifications.
