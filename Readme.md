# Hindi Seekho
~ by Snehil Kumar

> A Hindi Language Teaching App For Kids  
Find screenshots in the ss/ folder in the root  
directory.  

## About the App and How it works
The Hindi language teaching Android app for kids -  
HindiSeekho.  

This App teaches kids the Hindi Language.  

The app has 4 categories, namely Numbers, Family,  
Colors, and Phrases.  

In each category, there are list of words according  
to its category.  

Each list item contains a Hindi Word, written in  
English, the English translation of the Hindi  
word, and a small illustrative image on the left.

Each list item can be 'touched', to hear its Hindi  
pronunciation.

## Challenges and Solutions
> PROBLEM 1:  

Suppose we tap the list items in the Hindi-Seekho App,  
to listen to the Hindi pronunciation of the words.

Now, suppose there is a phone call or some other sound  
start playing on the phone.

Then, we will hear mixed sounds.  
This is not a good user experience.

SOLUTION:

Hence, I used the AudioManager Class of Android Studio.  
This made the current sound's volume lowered and  
restarted once the new sound has stopped playing.

> PROBLEM 2:  

If we press the back button to close the app,  
the sound continues to play.  

Now, suppose we open the app again and tap the list  
items to hear the sounds, we will again hear mixed  
sounds.  

This is because the previous sound's media player  
and its resources were in use.  
They were not yet released.  
And we started a new instance of the media player  
to play a new sound.  

Hence, there more memory usage than needed and was  
not a good user experience at all.  

SOLUTION:  

Here, I used a strategy.  
I freed up the resources previously in use before  
creating and using new resources to play a new sound.  

> Resources like memory and CPU are very important  
for a software to run fast, efficiently and to provide  
a good user experience.

## Skill Used
Java, Android Studio, XML (UI design)