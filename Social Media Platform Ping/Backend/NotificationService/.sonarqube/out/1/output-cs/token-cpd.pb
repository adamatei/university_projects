ç
gC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\NotificationService\Consumers\FollowConsumer.cs
	namespace		 	
NotificationService		
 
.		 
	Consumers		 '
{

 
public 

class 
FollowConsumer 
:  !
	IConsumer" +
<+ ,
FollowRequest, 9
>9 :
{ 
private 
readonly  
INotificationManager -
manager. 5
;5 6
public 
FollowConsumer 
(  
INotificationManager 2
manager3 :
): ;
{ 	
this 
. 
manager 
= 
manager "
;" #
} 	
public 
async 
Task 
Consume !
(! "
ConsumeContext" 0
<0 1
FollowRequest1 >
>> ?
context@ G
)G H
{ 	
var 
msg 
= 
context 
. 
Message %
;% &
Notification 
notification %
=& '
new( +
Notification, 8
{9 :
Content; B
=C D
$"E G
{G H
msgH K
.K L
FollowerL T
}T U)
 has requested to follow you.U r
"r s
,s t
Foru x
=y z
msg{ ~
.~ 
Followed	 á
,
á à
	CreatedOn
â í
=
ì î
DateTime
ï ù
.
ù û
Now
û °
}
° ¢
;
¢ £
await 
manager 
. 
CreateNotification ,
(, -
notification- 9
)9 :
;: ;
await 
Console 
. 
Out 
. 
WriteLineAsync ,
(, -
Convert- 4
.4 5
ToString5 =
(= >
msg> A
.A B
IdB D
)D E
)E F
;F G
} 	
} 
} ¨
eC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\NotificationService\Consumers\LikeConsumer.cs
	namespace		 	
NotificationService		
 
.		 
	Consumers		 '
{

 
public 

class 
LikeConsumer 
: 
	IConsumer  )
<) *
Like* .
>. /
{ 
private 
readonly  
INotificationManager -
manager. 5
;5 6
public 
LikeConsumer 
(  
INotificationManager 0
manager1 8
)8 9
{ 	
this 
. 
manager 
= 
manager "
;" #
} 	
public 
async 
Task 
Consume !
(! "
ConsumeContext" 0
<0 1
Like1 5
>5 6
context7 >
)> ?
{ 	
var 
msg 
= 
context 
. 
Message %
;% &
Notification 
notification )
=* +
new, /
Notification0 <
{= >
Content? F
=G H
$"I K
{K L
msgL O
.O P
TotalP U
}U V#
 users liked your post.V m
"m n
,n o
	CreatedOnp y
=z {
DateTime	| Ñ
.
Ñ Ö
Now
Ö à
,
à â
For
ä ç
=
é è
msg
ê ì
.
ì î
LikedUsername
î °
}
¢ £
;
£ §
await 
manager 
. 
CreateNotification 0
(0 1
notification1 =
)= >
;> ?
await 
Console 
. 
Out !
.! "
WriteLineAsync" 0
(0 1
msg1 4
.4 5
Total5 :
+; <
$str= L
+M N
msgO R
.R S
LikedUsernameS `
+a b
$strc l
)l m
;m n
} 	
} 
} Ú
hC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\NotificationService\Consumers\MentionConsumer.cs
	namespace		 	
NotificationService		
 
.		 
	Consumers		 '
{

 
public 

class 
MentionConsumer  
:! "
	IConsumer# ,
<, -
MentionNotification- @
>@ A
{ 
private 
readonly  
INotificationManager -
manager. 5
;5 6
public 
MentionConsumer 
(  
INotificationManager 3
manager4 ;
); <
{ 	
this 
. 
manager 
= 
manager "
;" #
} 	
public 
async 
Task 
Consume !
(! "
ConsumeContext" 0
<0 1
MentionNotification1 D
>D E
contextF M
)M N
{ 	
var 
msg 
= 
context 
. 
Message %
;% &
Notification 
notification %
=& '
new( +
Notification, 8
{9 :
Content; B
=C D
$"E G
{G H
msgH K
.K L
	MentionerL U
}U V%
 mentioned you in a post.V o
"o p
,p q
Forr u
=v w
msgx {
.{ |
	Mentioned	| Ö
,
Ö Ü
	CreatedOn
á ê
=
ë í
DateTime
ì õ
.
õ ú
Now
ú ü
}
ü †
;
† °
await 
manager 
. 
CreateNotification ,
(, -
notification- 9
)9 :
;: ;
await 
Console 
. 
Out 
. 
WriteLineAsync ,
(, -
$str- 4
+5 6
msg7 :
.: ;
	Mentioner; D
+E F
$strG T
+U V
msgW Z
.Z [
	Mentioned[ d
+e f
$strg s
)s t
;t u
} 	
} 
} å
qC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\NotificationService\Controllers\NotificationController.cs
	namespace

 	
NotificationService


 
.

 
Controllers

 )
{ 
[ 
Route 

(
 
$str 
) 
] 
[ 
ApiController 
] 
public 

class "
NotificationController '
:( )
ControllerBase* 8
{ 
private 
readonly  
INotificationManager -
service. 5
;5 6
public "
NotificationController %
(% & 
INotificationManager& :
service; B
)B C
{ 	
this 
. 
service 
= 
service "
;" #
} 	
[ 	
HttpGet	 
( 
$str 
) 
] 
public 
IEnumerable 
< 
Notification '
>' (#
GetNotificationsForUser) @
(@ A
stringA G
usernameH P
)P Q
{ 	
return 
service 
. #
GetNotificationsForUser 2
(2 3
username3 ;
); <
;< =
} 	
[ 	
HttpGet	 
( 
$str 
) 
] 
public   
async   
Task   
<   
Notification   &
>  & '
GetNotificationById  ( ;
(  ; <
int  < ?
id  @ B
)  B C
{!! 	
return"" 
await"" 
service""  
.""  !
GetNotificationById""! 4
(""4 5
id""5 7
)""7 8
;""8 9
}## 	
[&& 	
HttpPost&&	 
]&& 
public'' 
async'' 
Task'' 
<'' 
Notification'' &
>''& '
Post''( ,
('', -
[''- .
FromBody''. 6
]''6 7
Notification''8 D
notification''E Q
)''Q R
{(( 	
return)) 
await)) 
service))  
.))  !
CreateNotification))! 3
())3 4
notification))4 @
)))@ A
;))A B
}** 	
[-- 	
HttpPut--	 
]-- 
public.. 
async.. 
Task.. 
<.. 
Notification.. &
>..& '
Put..( +
(..+ ,
[.., -
FromBody..- 5
]..5 6
Notification..7 C
notification..D P
)..P Q
{// 	
return00 
await00 
service00  
.00  !
UpdateNotification00! 3
(003 4
notification004 @
)00@ A
;00A B
}11 	
[44 	

HttpDelete44	 
(44 
$str44 
)44 
]44 
public55 
async55 
Task55 
<55 
bool55 
>55 
Delete55  &
(55& '
int55' *
id55+ -
)55- .
{66 	
return77 
await77 
service77  
.77  !
DeleteNotification77! 3
(773 4
id774 6
)776 7
;777 8
}88 	
}99 
}:: ˆ
iC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\NotificationService\DataAccess\DatabaseContext.cs
	namespace 	
NotificationService
 
. 

DataAccess (
{ 
public 

class 
DatabaseContext  
:  !
	DbContext" +
{ 
public 
virtual 
DbSet 
< 
Notification )
>) *
Notifications+ 8
{9 :
get; >
;> ?
set@ C
;C D
}E F
	protected

 
override

 
void

 
OnConfiguring

  -
(

- .#
DbContextOptionsBuilder

. E
optionsBuilder

F T
)

T U
{ 	
optionsBuilder 
. 
UseSqlServer '
(' (
$str -
+. /
$str 8
+9 :
$str 
+  !
$str 0
)0 1
;1 2
} 	
	protected 
override 
void 
OnModelCreating  /
(/ 0
ModelBuilder0 <
modelBuilder= I
)I J
{ 	
modelBuilder 
. 
Entity 
<  
Notification  ,
>, -
(- .
entity. 4
=>5 7
{ 
entity 
. 
HasKey 
( 
e 
=>  "
e# $
.$ %
Id% '
)' (
;( )
} 
) 
; 
} 	
} 
} º
hC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\NotificationService\DataAccess\FakeRepository.cs
	namespace 	
NotificationService
 
. 

DataAccess (
{ 
public 

class 
FakeRepository 
{ 
public 
List 
< 
Notification  
>  !
Notifications" /
{0 1
get2 5
;5 6
set7 :
;: ;
}< =
=> ?
new@ C
ListD H
<H I
NotificationI U
>U V
(V W
)W X
{Y Z
new[ ^
Notification_ k
{l m
Idn p
=q r
$nums t
,t u
Contentv }
=~ 
$str
Ä ó
,
ó ò
For
ô ú
=
ù û
$str
ü §
}
• ¶
,
¶ ß
new
® ´
Notification
¨ ∏
{
π ∫
Id
ª Ω
=
æ ø
$num
¿ ¡
,
¡ ¬
Content
√  
=
À Ã
$str
Õ Í
,
Í Î
For
Ï Ô
=
 Ò
$str
Ú ¯
}
˘ ˙
,
˙ ˚
new
¸ ˇ
Notification
Ä å
{
ç é
Id
è ë
=
í ì
$num
î ï
,
ï ñ
Content
ó û
=
ü †
$str
° ∂
,
∂ ∑
For
∏ ª
=
º Ω
$str
æ ƒ
}
≈ ∆
}
« »
;
» …
}		 
}

 ó

nC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\NotificationService\Interfaces\INotificationManager.cs
	namespace 	
NotificationService
 
. 

Interfaces (
{ 
public 

	interface  
INotificationManager )
{ 
List		 
<		 
Notification		 
>		 #
GetNotificationsForUser		 2
(		2 3
string		3 9
username		: B
)		B C
;		C D
Task

 
<

 
Notification

 
>

 
GetNotificationById

 .
(

. /
int

/ 2
id

3 5
)

5 6
;

6 7
Task 
< 
Notification 
> 
CreateNotification -
(- .
Notification. :
notification; G
)G H
;H I
Task 
< 
Notification 
> 
UpdateNotification -
(- .
Notification. :
notification; G
)G H
;H I
Task 
< 
bool 
> 
DeleteNotification %
(% &
int& )
id* ,
), -
;- .
} 
} Ò+
kC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\NotificationService\Managers\NotificationManager.cs
	namespace 	
NotificationService
 
. 
Managers &
{		 
public

 

class

 
NotificationManager

 $
:

% & 
INotificationManager

' ;
{ 
private 
readonly 
DatabaseContext (
db) +
;+ ,
public 
NotificationManager "
(" #
DatabaseContext# 2
db3 5
)5 6
{ 	
this 
. 
db 
= 
db 
; 
} 	
public 
async 
Task 
< 
Notification &
>& '
CreateNotification( :
(: ;
Notification; G
notificationH T
)T U
{ 	
foreach 
( 
var 
not 
in 
db  "
." #
Notifications# 0
.0 1
ToList1 7
(7 8
)8 9
)9 :
{ 
if 
( 
not 
. 
Id 
== 
notification )
.) *
Id* ,
), -
{ 
return 
null 
;  
} 
} 
await 
db 
. 
Notifications "
." #
AddAsync# +
(+ ,
notification, 8
)8 9
;9 :
await 
db 
. 
SaveChangesAsync %
(% &
)& '
;' (
return 
notification 
;  
}   	
public## 
async## 
Task## 
<## 
bool## 
>## 
DeleteNotification##  2
(##2 3
int##3 6
id##7 9
)##9 :
{$$ 	
foreach%% 
(%% 
var%% 
not%% 
in%% 
db%%  "
.%%" #
Notifications%%# 0
.%%0 1
ToList%%1 7
(%%7 8
)%%8 9
)%%9 :
{&& 
if'' 
('' 
not'' 
.'' 
Id'' 
=='' 
id''  
)''  !
{(( 
db)) 
.)) 
Notifications)) $
.))$ %
Remove))% +
())+ ,
not)), /
)))/ 0
;))0 1
await** 
db** 
.** 
SaveChangesAsync** -
(**- .
)**. /
;**/ 0
return++ 
true++ 
;++  
},, 
}-- 
return// 
false// 
;// 
}00 	
public33 
async33 
Task33 
<33 
Notification33 &
>33& '
GetNotificationById33( ;
(33; <
int33< ?
id33@ B
)33B C
{44 	
return55 
await55 
db55 
.55 
Notifications55 )
.55) *
	FindAsync55* 3
(553 4
id554 6
)556 7
;557 8
}66 	
public99 
List99 
<99 
Notification99  
>99  !#
GetNotificationsForUser99" 9
(999 :
string99: @
username99A I
)99I J
{:: 	
List;; 
<;; 
Notification;; 
>;; 
tempList;; '
=;;( )
new;;* -
List;;. 2
<;;2 3
Notification;;3 ?
>;;? @
(;;@ A
);;A B
;;;B C
foreach<< 
(<< 
var<< 
not<< 
in<< 
db<<  "
.<<" #
Notifications<<# 0
.<<0 1
ToList<<1 7
(<<7 8
)<<8 9
)<<9 :
{== 
if>> 
(>> 
not>> 
.>> 
For>> 
==>> 
username>> '
)>>' (
{?? 
tempList@@ 
.@@ 
Add@@  
(@@  !
not@@! $
)@@$ %
;@@% &
}AA 
}BB 
returnCC 
tempListCC 
;CC 
}DD 	
publicGG 
asyncGG 
TaskGG 
<GG 
NotificationGG &
>GG& '
UpdateNotificationGG( :
(GG: ;
NotificationGG; G
notificationGGH T
)GGT U
{HH 	
foreachII 
(II 
varII 
notII 
inII 
dbII  "
.II" #
NotificationsII# 0
.II0 1
ToListII1 7
(II7 8
)II8 9
)II9 :
{JJ 
ifKK 
(KK 
notKK 
.KK 
IdKK 
==KK 
notificationKK *
.KK* +
IdKK+ -
)KK- .
{LL 
notMM 
.MM 
ContentMM 
=MM  !
notificationMM" .
.MM. /
ContentMM/ 6
;MM6 7
notNN 
.NN 
ForNN 
=NN 
notificationNN *
.NN* +
ForNN+ .
;NN. /
awaitOO 
dbOO 
.OO 
SaveChangesAsyncOO -
(OO- .
)OO. /
;OO/ 0
returnPP 
notPP 
;PP 
}QQ 
}RR 
returnTT 
nullTT 
;TT 
}UU 	
}VV 
}WW ’
pC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\NotificationService\Migrations\20220307093638_initial.cs
	namespace 	
NotificationService
 
. 

Migrations (
{ 
public 

partial 
class 
initial  
:! "
	Migration# ,
{ 
	protected 
override 
void 
Up  "
(" #
MigrationBuilder# 3
migrationBuilder4 D
)D E
{		 	
migrationBuilder

 
.

 
CreateTable

 (
(

( )
name 
: 
$str %
,% &
columns 
: 
table 
=> !
new" %
{ 
Id 
= 
table 
. 
Column %
<% &
int& )
>) *
(* +
type+ /
:/ 0
$str1 6
,6 7
nullable8 @
:@ A
falseB G
)G H
. 

Annotation #
(# $
$str$ 8
,8 9
$str: @
)@ A
,A B
Content 
= 
table #
.# $
Column$ *
<* +
string+ 1
>1 2
(2 3
type3 7
:7 8
$str9 H
,H I
nullableJ R
:R S
trueT X
)X Y
,Y Z
	CreatedOn 
= 
table  %
.% &
Column& ,
<, -
DateTime- 5
>5 6
(6 7
type7 ;
:; <
$str= H
,H I
nullableJ R
:R S
falseT Y
)Y Z
,Z [
For 
= 
table 
.  
Column  &
<& '
string' -
>- .
(. /
type/ 3
:3 4
$str5 D
,D E
nullableF N
:N O
trueP T
)T U
} 
, 
constraints 
: 
table "
=># %
{ 
table 
. 

PrimaryKey $
($ %
$str% 7
,7 8
x9 :
=>; =
x> ?
.? @
Id@ B
)B C
;C D
} 
) 
; 
} 	
	protected 
override 
void 
Down  $
($ %
MigrationBuilder% 5
migrationBuilder6 F
)F G
{ 	
migrationBuilder 
. 
	DropTable &
(& '
name 
: 
$str %
)% &
;& '
} 	
} 
}   Ó
bC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\NotificationService\Models\Notification.cs
	namespace 	
NotificationService
 
. 
Models $
{ 
public 

class 
Notification 
{ 
public 
virtual 
int 
Id 
{ 
get  #
;# $
set% (
;( )
}* +
public 
virtual 
string 
Content %
{& '
get( +
;+ ,
set- 0
;0 1
}2 3
public		 
virtual		 
DateTime		 
	CreatedOn		  )
{		* +
get		, /
;		/ 0
set		1 4
;		4 5
}		6 7
public

 
virtual

 
string

 
For

 !
{

" #
get

$ '
;

' (
set

) ,
;

, -
}

. /
} 
} ∑

VC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\NotificationService\Program.cs
	namespace

 	
NotificationService


 
{ 
public 

class 
Program 
{ 
public 
static 
void 
Main 
(  
string  &
[& '
]' (
args) -
)- .
{ 	
CreateHostBuilder 
( 
args "
)" #
.# $
Build$ )
() *
)* +
.+ ,
Run, /
(/ 0
)0 1
;1 2
} 	
public 
static 
IHostBuilder "
CreateHostBuilder# 4
(4 5
string5 ;
[; <
]< =
args> B
)B C
=>D F
Host 
.  
CreateDefaultBuilder %
(% &
args& *
)* +
. $
ConfigureWebHostDefaults )
() *

webBuilder* 4
=>5 7
{ 

webBuilder 
. 

UseStartup )
<) *
Startup* 1
>1 2
(2 3
)3 4
;4 5
} 
) 
; 
} 
} Ë.
VC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\NotificationService\Startup.cs
	namespace 	
NotificationService
 
{ 
public 

class 
Startup 
{ 
public 
Startup 
( 
IConfiguration %
configuration& 3
)3 4
{ 	
Configuration 
= 
configuration )
;) *
} 	
public 
IConfiguration 
Configuration +
{, -
get. 1
;1 2
}3 4
public 
void 
ConfigureServices %
(% &
IServiceCollection& 8
services9 A
)A B
{   	
services!! 
.!! 
AddCors!! 
(!! 
)!! 
;!! 
services"" 
."" 
AddMassTransit"" #
(""# $
x""$ %
=>""& (
{## 
x$$ 
.$$ 
AddConsumer$$ 
<$$ 
FollowConsumer$$ ,
>$$, -
($$- .
)$$. /
;$$/ 0
x%% 
.%% 
AddConsumer%% 
<%% 
LikeConsumer%% *
>%%* +
(%%+ ,
)%%, -
;%%- .
x&& 
.&& 
AddConsumer&& 
<&& 
MentionConsumer&& -
>&&- .
(&&. /
)&&/ 0
;&&0 1
x'' 
.'' 
UsingRabbitMq'' 
(''  
(''  !
ctx''! $
,''$ %
cfg''& )
)'') *
=>''+ -
{(( 
cfg)) 
.)) 
Host)) 
()) 
$str)) @
)))@ A
;))A B
cfg,, 
.,, 
ReceiveEndpoint,, '
(,,' (
Configuration,,( 5
[,,5 6
$str,,6 H
],,H I
,,,I J
c,,K L
=>,,M O
{-- 
c.. 
... 
ConfigureConsumer.. +
<..+ ,
FollowConsumer.., :
>..: ;
(..; <
ctx..< ?
)..? @
;..@ A
}// 
)// 
;// 
cfg00 
.00 
ReceiveEndpoint00 '
(00' (
$str00( 7
,007 8
c009 :
=>00; =
{11 
c22 
.22 
ConfigureConsumer22 +
<22+ ,
MentionConsumer22, ;
>22; <
(22< =
ctx22= @
)22@ A
;22A B
}33 
)33 
;33 
cfg44 
.44 
ReceiveEndpoint44 '
(44' (
$str44( 4
,444 5
c446 7
=>448 :
{55 
c66 
.66 
ConfigureConsumer66 +
<66+ ,
LikeConsumer66, 8
>668 9
(669 :
ctx66: =
)66= >
;66> ?
}77 
)77 
;77 
}88 
)88 
;88 
}99 
)99 
;99 
services:: 
.:: '
AddMassTransitHostedService:: 0
(::0 1
)::1 2
;::2 3
services;; 
.;; 
AddDbContext;; !
<;;! "
DatabaseContext;;" 1
>;;1 2
(;;2 3
);;3 4
;;;4 5
services<< 
.<< 
AddTransient<< !
<<<! " 
INotificationManager<<" 6
,<<6 7
NotificationManager<<8 K
><<K L
(<<L M
)<<M N
;<<N O
services== 
.== 
AddControllers== #
(==# $
)==$ %
;==% &
services>> 
.>> 
AddSwaggerGen>> "
(>>" #
c>># $
=>>>% '
{?? 
c@@ 
.@@ 

SwaggerDoc@@ 
(@@ 
$str@@ !
,@@! "
new@@# &
OpenApiInfo@@' 2
{@@3 4
Title@@5 :
=@@; <
$str@@= R
,@@R S
Version@@T [
=@@\ ]
$str@@^ b
}@@c d
)@@d e
;@@e f
}AA 
)AA 
;AA 
}BB 	
publicEE 
voidEE 
	ConfigureEE 
(EE 
IApplicationBuilderEE 1
appEE2 5
,EE5 6
IWebHostEnvironmentEE7 J
envEEK N
)EEN O
{FF 	
ifGG 
(GG 
envGG 
.GG 
IsDevelopmentGG !
(GG! "
)GG" #
)GG# $
{HH 
appII 
.II %
UseDeveloperExceptionPageII -
(II- .
)II. /
;II/ 0
appJJ 
.JJ 

UseSwaggerJJ 
(JJ 
)JJ  
;JJ  !
appKK 
.KK 
UseSwaggerUIKK  
(KK  !
cKK! "
=>KK# %
cKK& '
.KK' (
SwaggerEndpointKK( 7
(KK7 8
$strKK8 R
,KKR S
$strKKT l
)KKl m
)KKm n
;KKn o
}LL 
appNN 
.NN 

UseRoutingNN 
(NN 
)NN 
;NN 
appPP 
.PP 
UseAuthorizationPP  
(PP  !
)PP! "
;PP" #
appQQ 
.QQ 
UseCorsQQ 
(QQ 
optionsRR 
=>RR 
optionsRR  
.RR  !
WithOriginsRR! ,
(RR, -
$strRR- D
)RRD E
.RRE F
AllowAnyMethodRRF T
(RRT U
)RRU V
.RRV W
AllowAnyHeaderRRW e
(RRe f
)RRf g
)SS
 
;SS 
appTT 
.TT 
UseEndpointsTT 
(TT 
	endpointsTT &
=>TT' )
{UU 
	endpointsVV 
.VV 
MapControllersVV (
(VV( )
)VV) *
;VV* +
}WW 
)WW 
;WW 
}XX 	
}YY 
}ZZ 