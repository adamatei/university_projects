½
XC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\MassTransitModels\BlockUpdate.cs
	namespace 	
MassTransitModels
 
{ 
public		 

class		 
BlockUpdate		 
{

 
public 
string 
Username 
{  
get! $
;$ %
set& )
;) *
}+ ,
public 
bool 
	isBlocked 
{ 
get  #
;# $
set% (
;( )
}* +
} 
} Œ
ZC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\MassTransitModels\FollowRequest.cs
	namespace 	
MassTransitModels
 
{ 
public 

class 
FollowRequest 
{ 
public 
int 
Id 
{ 
get 
; 
set  
;  !
}" #
public 
string 
Follower 
{  
get! $
;$ %
set& )
;) *
}+ ,
public		 
string		 
Followed		 
{		  
get		! $
;		$ %
set		& )
;		) *
}		+ ,
public

 
bool

 

isAccepted

 
{

  
get

! $
;

$ %
set

& )
;

) *
}

+ ,
public 
bool 
	isPending 
{ 
get  #
;# $
set% (
;( )
}* +
} 
} ÿ
QC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\MassTransitModels\Like.cs
	namespace 	
MassTransitModels
 
{ 
public		 

class		 
Like		 
{

 
public 
int 
Total 
{ 
get 
; 
set  #
;# $
}% &
public 
string 
LikedUsername #
{$ %
get& )
;) *
set+ .
;. /
}0 1
public 
DateTime 
LikedOn 
{  !
get" %
;% &
set' *
;* +
}, -
public 
int 
PostId 
{ 
get 
;  
set! $
;$ %
}& '
public 
string 
PostText 
{  
get! $
;$ %
set& )
;) *
}+ ,
} 
} ¤
`C:\Users\ADA\Documents\GitHub\ping-social-media\Backend\MassTransitModels\MentionNotification.cs
	namespace 	
MassTransitModels
 
{ 
public		 

class		 
MentionNotification		 $
{

 
public 
string 
	Mentioner 
{  !
get" %
;% &
set' *
;* +
}, -
public 
string 
	Mentioned 
{  !
get" %
;% &
set' *
;* +
}, -
public 
int 
PostId 
{ 
get 
;  
set! $
;$ %
}& '
public 
string 
PostText 
{  
get! $
;$ %
set& )
;) *
}+ ,
public 
DateTime 
MentionedOn #
{$ %
get& )
;) *
set+ .
;. /
}0 1
} 
} °
_C:\Users\ADA\Documents\GitHub\ping-social-media\Backend\MassTransitModels\RegistrationUpdate.cs
	namespace 	
MassTransitModels
 
{ 
public		 

class		 
RegistrationUpdate		 #
{

 
public 
string 
Username 
{  
get! $
;$ %
set& )
;) *
}+ ,
} 
} 