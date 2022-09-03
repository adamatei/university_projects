Ä
nC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\AuthenticationService\Consumers\BlockUpdateConsumer.cs
	namespace 	!
AuthenticationService
 
.  
	Consumers  )
{ 
public 

class 
BlockUpdateConsumer $
:% &
	IConsumer' 0
<0 1
BlockUpdate1 <
>< =
{		 
private

 
readonly

 %
IJWTAuthenticationManager

 2
manager

3 :
;

: ;
public 
BlockUpdateConsumer "
(" #%
IJWTAuthenticationManager# <
manager= D
)D E
{ 	
this 
. 
manager 
= 
manager "
;" #
} 	
public 
async 
Task 
Consume !
(! "
ConsumeContext" 0
<0 1
BlockUpdate1 <
>< =
context> E
)E F
{ 	
var 
msg 
= 
context 
. 
Message %
;% &
try 
{ 
manager 
. 
UpdateAccountAccess +
(+ ,
msg, /
./ 0
Username0 8
,8 9
msg: =
.= >
	isBlocked> G
)G H
;H I
await 
Console 
. 
Out !
.! "
WriteLineAsync" 0
(0 1
msg1 4
.4 5
Username5 =
+> ?
$str@ N
+O P
msgQ T
.T U
	isBlockedU ^
)^ _
;_ `
} 
catch 
( 
	Exception 
e 
) 
{ 
await 
Console 
. 
Out !
.! "
WriteLineAsync" 0
(0 1
e1 2
.2 3
Message3 :
): ;
;; <
} 
} 	
} 
} ø$
uC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\AuthenticationService\Controllers\AuthenticationController.cs
	namespace 	!
AuthenticationService
 
.  
Controllers  +
{ 
[ 
Route 

(
 
$str 
) 
] 
[ 
ApiController 
] 
public 

class $
AuthenticationController )
:* +
ControllerBase, :
{ 
private 
readonly %
IJWTAuthenticationManager 2$
jwtAuthenticationManager3 K
;K L
IPublishEndpoint 
_publishEndpoint )
;) *
public $
AuthenticationController '
(' (%
IJWTAuthenticationManager( A$
jwtAuthenticationManagerB Z
,Z [
IPublishEndpoint\ l
publishEindpointm }
)} ~
{ 	
this 
. $
jwtAuthenticationManager )
=* +$
jwtAuthenticationManager, D
;D E
_publishEndpoint 
= 
publishEindpoint /
;/ 0
} 	
[ 	
	Authorize	 
] 
[ 	
HttpGet	 
] 
public 
IEnumerable 
< 
string !
>! "
Get# &
(& '
)' (
{ 	
return   
new   
string   
[   
]   
{    !
$str  " *
,  * +
$str  , 4
}  5 6
;  6 7
}!! 	
[$$ 	
HttpGet$$	 
($$ 
$str$$ 
)$$ 
]$$ 
public%% 
string%% 
Get%% 
(%% 
string%%  
id%%! #
)%%# $
{&& 	
EncodingAlgorithm'' 
alg'' !
=''" #
new''$ '
EncodingAlgorithm''( 9
(''9 :
)'': ;
;''; <
return(( 
alg(( 
.(( 
EncryptTripleDES(( '
(((' (
id((( *
)((* +
;((+ ,
})) 	
[++ 	
AllowAnonymous++	 
]++ 
[,, 	
HttpPost,,	 
(,, 
$str,, 
),, 
],, 
public-- 
async-- 
Task-- 
<-- 
bool-- 
>-- 
Register--  (
(--( )
[--) *
FromBody--* 2
]--2 3
UserCredentials--4 C
userCred--D L
)--L M
{.. 	
RegistrationUpdate// 
reg// "
=//# $
new//% (
RegistrationUpdate//) ;
{//< =
Username//> F
=//G H
userCred//I Q
.//Q R
Username//R Z
}//Z [
;//[ \
await00 
_publishEndpoint00 "
.00" #
Publish00# *
<00* +
RegistrationUpdate00+ =
>00= >
(00> ?
reg00? B
)00B C
;00C D
var11 
result11 
=11 
await11 $
jwtAuthenticationManager11 7
.117 8
Register118 @
(11@ A
userCred11A I
.11I J
Username11J R
,11R S
userCred11T \
.11\ ]
Password11] e
)11e f
;11f g
if22 
(22 
result22 
!=22 
null22 
)22 
{22  
return22! '
true22( ,
;22, -
}22. /
return33 
false33 
;33 
}44 	
[66 	
AllowAnonymous66	 
]66 
[77 	
HttpPost77	 
(77 
$str77 !
)77! "
]77" #
public88 
IActionResult88 
Authenticate88 )
(88) *
[88* +
FromBody88+ 3
]883 4
UserCredentials885 D
userCred88E M
)88M N
{99 	
var:: 
token:: 
=:: $
jwtAuthenticationManager:: 0
.::0 1
Authenticate::1 =
(::= >
userCred::> F
.::F G
Username::G O
,::O P
userCred::Q Y
.::Y Z
Password::Z b
)::b c
;::c d
if;; 
(;; 
token;; 
==;; 
null;; 
);; 
return;; #
Unauthorized;;$ 0
(;;0 1
);;1 2
;;;2 3
return<< 
Ok<< 
(<< 
token<< 
)<< 
;<< 
}== 	
}>> 
}?? Ö
kC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\AuthenticationService\DataAccess\DatabaseContext.cs
	namespace 	!
AuthenticationService
 
.  

DataAccess  *
{ 
public 

class 
DatabaseContext  
:! "
	DbContext# ,
{ 
public 
DbSet 
< 
Account 
> 
Accounts &
{' (
get) ,
;, -
set. 1
;1 2
}3 4
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
$str :
+; <
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
<  
Account  '
>' (
(( )
entity) /
=>0 2
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
} Ö
uC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\AuthenticationService\Interfaces\IJWTAuthenticationManager.cs
	namespace 	!
AuthenticationService
 
{ 
public 

	interface %
IJWTAuthenticationManager .
{ 
string 
Authenticate 
( 
string "
username# +
,+ ,
string- 3
password4 <
)< =
;= >
Task		 
<		 
string		 
>		 
Register		 
(		 
string		 $
username		% -
,		- .
string		/ 5
password		6 >
)		> ?
;		? @
Task

 
<

 
Account

 
>

 
UpdateAccountAccess

 )
(

) *
string

* 0
username

1 9
,

9 :
bool

; ?
	isBlocked

@ I
)

I J
;

J K
} 
} òH
hC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\AuthenticationService\Logic\EncodingAlgorithm.cs
	namespace 	!
AuthenticationService
 
{ 
public 

class 
EncodingAlgorithm "
{ 
public		 
string		 
EncodeCustom		 "
(		" #
string		# )
Data		* .
)		. /
{

 	
try 
{ 
byte 
[ 
] 
oByte 
= 
new "
byte# '
[' (
Data( ,
., -
Length- 3
]3 4
;4 5
oByte 
= 
System 
. 
Text #
.# $
Encoding$ ,
., -
UTF8- 1
.1 2
GetBytes2 :
(: ;
Data; ?
)? @
;@ A
string 
EncodedData "
=# $
$str% )
+* +
Convert, 3
.3 4
ToBase64String4 B
(B C
oByteC H
)H I
;I J
return 
EncodedData "
;" #
} 
catch 
( 
	Exception 
ex 
)  
{ 
throw 
new 
	Exception #
(# $
ex$ &
.& '
Message' .
). /
;/ 0
} 
} 	
public 
string 
DecodeCustom "
(" #
string# )
Data* .
). /
{ 	
try 
{ 
System 
. 
Text 
. 
UTF8Encoding (
oUTF8Encoding) 6
=7 8
new9 <
System= C
.C D
TextD H
.H I
UTF8EncodingI U
(U V
)V W
;W X
System 
. 
Text 
. 
Decoder #
oDecoder$ ,
=- .
oUTF8Encoding/ <
.< =

GetDecoder= G
(G H
)H I
;I J
byte 
[ 
] 
oByte 
= 
Convert &
.& '
FromBase64String' 7
(7 8
Data8 <
)< =
;= >
int 
	CharCount 
= 
oDecoder  (
.( )
GetCharCount) 5
(5 6
oByte6 ;
,; <
$num= >
,> ?
oByte@ E
.E F
LengthF L
)L M
;M N
char   
[   
]   
DecodedChar   "
=  # $
new  % (
char  ) -
[  - .
	CharCount  . 7
]  7 8
;  8 9
oDecoder!! 
.!! 
GetChars!! !
(!!! "
oByte!!" '
,!!' (
$num!!) *
,!!* +
oByte!!, 1
.!!1 2
Length!!2 8
,!!8 9
DecodedChar!!: E
,!!E F
$num!!G H
)!!H I
;!!I J
string"" 
DecodedData"" "
=""# $
new""% (
String"") /
(""/ 0
DecodedChar""0 ;
)""; <
;""< =
return## 
DecodedData## "
;##" #
}$$ 
catch%% 
(%% 
	Exception%% 
ex%% 
)%%  
{&& 
throw'' 
new'' 
	Exception'' #
(''# $
ex''$ &
.''& '
Message''' .
)''. /
;''/ 0
}(( 
})) 	
public,, 
string,, 
EncryptTripleDES,, &
(,,& '
string,,' -
	PlainText,,. 7
),,7 8
{-- 	
byte// 
[// 
]// 
toEncryptedArray// #
=//$ %
UTF8Encoding//& 2
.//2 3
UTF8//3 7
.//7 8
GetBytes//8 @
(//@ A
	PlainText//A J
)//J K
;//K L$
MD5CryptoServiceProvider00 $
objMD5CryptoService00% 8
=009 :
new00; >$
MD5CryptoServiceProvider00? W
(00W X
)00X Y
;00Y Z
var11 
securityKey11 
=11 
Environment11 )
.11) *"
GetEnvironmentVariable11* @
(11@ A
$str11A O
)11O P
;11P Q
byte22 
[22 
]22 
securityKeyArray22 #
=22$ %
objMD5CryptoService22& 9
.229 :
ComputeHash22: E
(22E F
UTF8Encoding22F R
.22R S
UTF822S W
.22W X
GetBytes22X `
(22` a
securityKey22a l
)22l m
)22m n
;22n o
objMD5CryptoService33 
.33  
Clear33  %
(33% &
)33& '
;33' (
var44 %
objTripleDESCryptoService44 )
=44* +
new44, /*
TripleDESCryptoServiceProvider440 N
(44N O
)44O P
;44P Q%
objTripleDESCryptoService55 %
.55% &
Key55& )
=55* +
securityKeyArray55, <
;55< =%
objTripleDESCryptoService66 %
.66% &
Mode66& *
=66+ ,

CipherMode66- 7
.667 8
ECB668 ;
;66; <%
objTripleDESCryptoService77 %
.77% &
Padding77& -
=77. /
PaddingMode770 ;
.77; <
PKCS777< A
;77A B
var88 
objCrytpoTransform88 "
=88# $%
objTripleDESCryptoService88% >
.88> ?
CreateEncryptor88? N
(88N O
)88O P
;88P Q
byte99 
[99 
]99 
resultArray99 
=99  
objCrytpoTransform99! 3
.993 4
TransformFinalBlock994 G
(99G H
toEncryptedArray99H X
,99X Y
$num99Z [
,99[ \
toEncryptedArray99] m
.99m n
Length99n t
)99t u
;99u v%
objTripleDESCryptoService:: %
.::% &
Clear::& +
(::+ ,
)::, -
;::- .
return;; 
Convert;; 
.;; 
ToBase64String;; )
(;;) *
resultArray;;* 5
,;;5 6
$num;;7 8
,;;8 9
resultArray;;: E
.;;E F
Length;;F L
);;L M
;;;M N
}<< 	
public?? 
string?? 
DecryptTripleDES?? &
(??& '
string??' -

CipherText??. 8
)??8 9
{@@ 	
byteAA 
[AA 
]AA 
toEncryptArrayAA !
=AA" #
ConvertAA$ +
.AA+ ,
FromBase64StringAA, <
(AA< =

CipherTextAA= G
)AAG H
;AAH I$
MD5CryptoServiceProviderBB $
objMD5CryptoServiceBB% 8
=BB9 :
newBB; >$
MD5CryptoServiceProviderBB? W
(BBW X
)BBX Y
;BBY Z
varCC 
securityKeyCC 
=CC 
EnvironmentCC )
.CC) *"
GetEnvironmentVariableCC* @
(CC@ A
$strCCA O
)CCO P
;CCP Q
byteDD 
[DD 
]DD 
securityKeyArrayDD #
=DD$ %
objMD5CryptoServiceDD& 9
.DD9 :
ComputeHashDD: E
(DDE F
UTF8EncodingDDF R
.DDR S
UTF8DDS W
.DDW X
GetBytesDDX `
(DD` a
securityKeyDDa l
)DDl m
)DDm n
;DDn o
objMD5CryptoServiceEE 
.EE  
ClearEE  %
(EE% &
)EE& '
;EE' (
varFF %
objTripleDESCryptoServiceFF )
=FF* +
newFF, /*
TripleDESCryptoServiceProviderFF0 N
(FFN O
)FFO P
;FFP Q%
objTripleDESCryptoServiceGG %
.GG% &
KeyGG& )
=GG* +
securityKeyArrayGG, <
;GG< =%
objTripleDESCryptoServiceHH %
.HH% &
ModeHH& *
=HH+ ,

CipherModeHH- 7
.HH7 8
ECBHH8 ;
;HH; <%
objTripleDESCryptoServiceII %
.II% &
PaddingII& -
=II. /
PaddingModeII0 ;
.II; <
PKCS7II< A
;IIA B
varJJ 
objCrytpoTransformJJ "
=JJ# $%
objTripleDESCryptoServiceJJ% >
.JJ> ?
CreateDecryptorJJ? N
(JJN O
)JJO P
;JJP Q
byteKK 
[KK 
]KK 
resultArrayKK 
=KK  
objCrytpoTransformKK! 3
.KK3 4
TransformFinalBlockKK4 G
(KKG H
toEncryptArrayKKH V
,KKV W
$numKKX Y
,KKY Z
toEncryptArrayKK[ i
.KKi j
LengthKKj p
)KKp q
;KKq r%
objTripleDESCryptoServiceLL %
.LL% &
ClearLL& +
(LL+ ,
)LL, -
;LL- .
returnMM 
UTF8EncodingMM 
.MM  
UTF8MM  $
.MM$ %
	GetStringMM% .
(MM. /
resultArrayMM/ :
)MM: ;
;MM; <
}NN 	
}OO 
}PP ä9
oC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\AuthenticationService\Logic\JWTAuthenticationManager.cs
	namespace 	!
AuthenticationService
 
{ 
public 

class $
JWTAuthenticationManager )
:* +%
IJWTAuthenticationManager, E
{ 
private 
readonly 
string 
key  #
;# $
private 
readonly 
EncodingAlgorithm *
	algorithm+ 4
=5 6
new7 :
EncodingAlgorithm; L
(L M
)M N
;N O
private 
readonly 
DatabaseContext (
db) +
=, -
new. 1
DatabaseContext2 A
(A B
)B C
;C D
public $
JWTAuthenticationManager '
(' (
string( .
key/ 2
)2 3
{ 	
this 
. 
key 
= 
key 
; 
} 	
public 
string 
Authenticate "
(" #
string# )
username* 2
,2 3
string4 :
password; C
)C D
{ 	
string 
encoded_password "
=# $
	algorithm% .
.. /
EncryptTripleDES/ ?
(? @
password@ H
)H I
;I J
if 
( 
! 
db 
. 
Accounts 
. 
Any 
(  
u  !
=>" $
u% &
.& '
Username' /
==0 2
username3 ;
&&< >
u? @
.@ A
PasswordA I
==J L
encoded_passwordM ]
)] ^
)^ _
{ 
return 
null 
; 
} 
if 
( 
db 
. 
Accounts 
. 
Any 
( 
u  
=>! #
u$ %
.% &
Username& .
==/ 1
username2 :
&&; =
u> ?
.? @
Password@ H
==I K
encoded_passwordL \
&&] _
u` a
.a b
	isBlockedb k
==l n
trueo s
)s t
)t u
{   
return!! 
null!! 
;!! 
}"" 
var$$ 
tokenHandler$$ 
=$$ 
new$$ "#
JwtSecurityTokenHandler$$# :
($$: ;
)$$; <
;$$< =
var%% 
tokenKey%% 
=%% 
Encoding%% #
.%%# $
ASCII%%$ )
.%%) *
GetBytes%%* 2
(%%2 3
key%%3 6
)%%6 7
;%%7 8
var&& 
tokenDescriptor&& 
=&&  !
new&&" %#
SecurityTokenDescriptor&&& =
{'' 
Subject(( 
=(( 
new(( 
ClaimsIdentity(( ,
(((, -
new((- 0
Claim((1 6
[((6 7
]((7 8
{)) 
new** 
Claim** 
(** 

ClaimTypes** (
.**( )
Name**) -
,**- .
username**/ 7
)**7 8
}++ 
)++ 
,++ 
Expires,, 
=,, 
DateTime,, "
.,," #
UtcNow,,# )
.,,) *
AddHours,,* 2
(,,2 3
$num,,3 4
),,4 5
,,,5 6
SigningCredentials-- "
=--# $
new--% (
SigningCredentials--) ;
(--; <
new--< ? 
SymmetricSecurityKey--@ T
(--T U
tokenKey--U ]
)--] ^
,--^ _
SecurityAlgorithms.. "
..." #
HmacSha256Signature..# 6
)..6 7
}// 
;// 
var00 
token00 
=00 
tokenHandler00 $
.00$ %
CreateToken00% 0
(000 1
tokenDescriptor001 @
)00@ A
;00A B
return11 
tokenHandler11 
.11  

WriteToken11  *
(11* +
token11+ 0
)110 1
;111 2
}22 	
public44 
async44 
Task44 
<44 
string44  
>44  !
Register44" *
(44* +
string44+ 1
username442 :
,44: ;
string44< B
password44C K
)44K L
{55 	
if66 
(66 
!66 
db66 
.66 
Accounts66 
.66 
Any66  
(66  !
u66! "
=>66# %
u66& '
.66' (
Username66( 0
==661 3
username664 <
)66< =
)66= >
{77 
string88 
encoded_password88 '
=88( )
	algorithm88* 3
.883 4
EncryptTripleDES884 D
(88D E
password88E M
)88M N
;88N O
Account99 
account99 
=99  !
new99" %
Account99& -
{99. /
Username990 8
=999 :
username99; C
,99C D
Password99E M
=99N O
encoded_password99P `
,99` a
	isBlocked99b k
=99l m
false99n s
}99s t
;99t u
await:: 
db:: 
.:: 
Accounts:: !
.::! "
AddAsync::" *
(::* +
account::+ 2
)::2 3
;::3 4
Console;; 
.;; 
	WriteLine;; !
(;;! "
db;;" $
.;;$ %
Accounts;;% -
.;;- .
ToList;;. 4
(;;4 5
);;5 6
.;;6 7
ToString;;7 ?
(;;? @
);;@ A
);;A B
;;;B C
await<< 
db<< 
.<< 
SaveChangesAsync<< )
(<<) *
)<<* +
;<<+ ,
return== 
username== 
;==  
}>> 
return?? 
null?? 
;?? 
}@@ 	
publicBB 
asyncBB 
TaskBB 
<BB 
AccountBB !
>BB! "
UpdateAccountAccessBB# 6
(BB6 7
stringBB7 =
usernameBB> F
,BBF G
boolBBH L
	isBlockedBBM V
)BBV W
{CC 	
foreachDD 
(DD 
AccountDD 
aDD 
inDD !
dbDD" $
.DD$ %
AccountsDD% -
.DD- .
ToListDD. 4
(DD4 5
)DD5 6
)DD6 7
{EE 
ifFF 
(FF 
aFF 
.FF 
UsernameFF 
==FF  
usernameFF! )
)FF) *
{GG 
aHH 
.HH 
	isBlockedHH 
=HH  !
	isBlockedHH" +
;HH+ ,
dbII 
.II 
UpdateII 
(II 
aII 
)II  
;II  !
awaitJJ 
dbJJ 
.JJ 
SaveChangesAsyncJJ -
(JJ- .
)JJ. /
;JJ/ 0
returnKK 
aKK 
;KK 
}LL 
}MM 
returnNN 
nullNN 
;NN 
}OO 	
}PP 
}QQ Ý
sC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\AuthenticationService\Migrations\20220304103132_initial2.cs
	namespace 	!
AuthenticationService
 
.  

Migrations  *
{ 
public 

partial 
class 
initial2 !
:" #
	Migration$ -
{ 
	protected 
override 
void 
Up  "
(" #
MigrationBuilder# 3
migrationBuilder4 D
)D E
{ 	
migrationBuilder		 
.		 
CreateTable		 (
(		( )
name

 
:

 
$str

  
,

  !
columns 
: 
table 
=> !
new" %
{ 
Id 
= 
table 
. 
Column %
<% &
int& )
>) *
(* +
type+ /
:/ 0
$str1 6
,6 7
nullable8 @
:@ A
falseB G
)G H
. 

Annotation #
(# $
$str$ 8
,8 9
$str: @
)@ A
,A B
Username 
= 
table $
.$ %
Column% +
<+ ,
string, 2
>2 3
(3 4
type4 8
:8 9
$str: I
,I J
nullableK S
:S T
trueU Y
)Y Z
,Z [
Password 
= 
table $
.$ %
Column% +
<+ ,
string, 2
>2 3
(3 4
type4 8
:8 9
$str: I
,I J
nullableK S
:S T
trueU Y
)Y Z
,Z [
	isBlocked 
= 
table  %
.% &
Column& ,
<, -
bool- 1
>1 2
(2 3
type3 7
:7 8
$str9 >
,> ?
nullable@ H
:H I
falseJ O
)O P
} 
, 
constraints 
: 
table "
=># %
{ 
table 
. 

PrimaryKey $
($ %
$str% 2
,2 3
x4 5
=>6 8
x9 :
.: ;
Id; =
)= >
;> ?
} 
) 
; 
} 	
	protected 
override 
void 
Down  $
($ %
MigrationBuilder% 5
migrationBuilder6 F
)F G
{ 	
migrationBuilder 
. 
	DropTable &
(& '
name 
: 
$str  
)  !
;! "
} 	
} 
} –
_C:\Users\ADA\Documents\GitHub\ping-social-media\Backend\AuthenticationService\Models\Account.cs
	namespace 	!
AuthenticationService
 
.  
Models  &
{ 
public 

class 
Account 
{ 
public 
int 
Id 
{ 
get 
; 
set  
;  !
}" #
public 
string 
Username 
{  
get! $
;$ %
set& )
;) *
}+ ,
public 
string 
Password 
{  
get! $
;$ %
set& )
;) *
}+ ,
public 
bool 
	isBlocked 
{ 
get  #
;# $
set% (
;( )
}* +
}		 
}

 Õ
gC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\AuthenticationService\Models\UserCredentials.cs
	namespace 	!
AuthenticationService
 
{ 
public 

class 
UserCredentials  
{ 
public 
string 
Username 
{  
get! $
;$ %
set& )
;) *
}+ ,
public 
string 
Password 
{  
get! $
;$ %
set& )
;) *
}+ ,
} 
}		 »

XC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\AuthenticationService\Program.cs
	namespace

 	!
AuthenticationService
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
} ¢4
XC:\Users\ADA\Documents\GitHub\ping-social-media\Backend\AuthenticationService\Startup.cs
	namespace 	!
AuthenticationService
 
{ 
public 

class 
Startup 
{ 
public 
Startup 
( 
IConfiguration %
configuration& 3
)3 4
{ 	
Configuration 
= 
configuration )
;) *
} 	
public 
IConfiguration 
Configuration +
{, -
get. 1
;1 2
}3 4
public   
void   
ConfigureServices   %
(  % &
IServiceCollection  & 8
services  9 A
)  A B
{!! 	
services"" 
."" 
AddCors"" 
("" 
)"" 
;"" 
services## 
.## 
AddMassTransit## #
(### $
x##$ %
=>##& (
{$$ 
x%% 
.%% 
AddConsumer%% 
<%% 
BlockUpdateConsumer%% 1
>%%1 2
(%%2 3
)%%3 4
;%%4 5
x&& 
.&& 
UsingRabbitMq&& 
(&&  
(&&  !
ctx&&! $
,&&$ %
cfg&&& )
)&&) *
=>&&+ -
{'' 
cfg(( 
.(( 
Host(( 
((( 
$str(( @
)((@ A
;((A B
cfg** 
.** 
ReceiveEndpoint** '
(**' (
$str**( 8
,**8 9
c**: ;
=>**< >
{++ 
c,, 
.,, 
ConfigureConsumer,, +
<,,+ ,
BlockUpdateConsumer,,, ?
>,,? @
(,,@ A
ctx,,A D
),,D E
;,,E F
}-- 
)-- 
;-- 
}.. 
).. 
;.. 
}// 
)// 
;// 
services00 
.00 '
AddMassTransitHostedService00 0
(000 1
)001 2
;002 3
services11 
.11 
AddDbContext11 !
<11! "
DatabaseContext11" 1
>111 2
(112 3
)113 4
;114 5
services33 
.33 
AddControllers33 #
(33# $
)33$ %
;33% &
var44 
key44 
=44 
$str44 +
;44+ ,
services55 
.55 
AddAuthentication55 &
(55& '
x55' (
=>55) +
{66 
x77 
.77 %
DefaultAuthenticateScheme77 +
=77, -
JwtBearerDefaults77. ?
.77? @ 
AuthenticationScheme77@ T
;77T U
x88 
.88 "
DefaultChallengeScheme88 (
=88) *
JwtBearerDefaults88+ <
.88< = 
AuthenticationScheme88= Q
;88Q R
}99 
)99 
.99 
AddJwtBearer99 
(99 
x99 
=>99  
{:: 
x;; 
.;;  
RequireHttpsMetadata;; &
=;;' (
false;;) .
;;;. /
x<< 
.<< 
	SaveToken<< 
=<< 
true<< "
;<<" #
x== 
.== %
TokenValidationParameters== +
===, -
new==. 1%
TokenValidationParameters==2 K
{>> $
ValidateIssuerSigningKey?? ,
=??- .
true??/ 3
,??3 4
IssuerSigningKey@@ $
=@@% &
new@@' * 
SymmetricSecurityKey@@+ ?
(@@? @
Encoding@@@ H
.@@H I
ASCII@@I N
.@@N O
GetBytes@@O W
(@@W X
key@@X [
)@@[ \
)@@\ ]
,@@] ^
ValidateIssuerAA "
=AA# $
falseAA% *
,AA* +
ValidateAudienceBB $
=BB% &
falseBB' ,
}CC 
;CC 
}DD 
)DD 
;DD 
servicesEE 
.EE 
AddSingletonEE !
<EE! "%
IJWTAuthenticationManagerEE" ;
>EE; <
(EE< =
newEE= @$
JWTAuthenticationManagerEEA Y
(EEY Z
keyEEZ ]
)EE] ^
)EE^ _
;EE_ `
servicesFF 
.FF 
AddSwaggerGenFF "
(FF" #
cFF# $
=>FF% '
{GG 
cHH 
.HH 

SwaggerDocHH 
(HH 
$strHH !
,HH! "
newHH# &
OpenApiInfoHH' 2
{HH3 4
TitleHH5 :
=HH; <
$strHH= T
,HHT U
VersionHHV ]
=HH^ _
$strHH` d
}HHe f
)HHf g
;HHg h
}II 
)II 
;II 
}JJ 	
publicMM 
voidMM 
	ConfigureMM 
(MM 
IApplicationBuilderMM 1
appMM2 5
,MM5 6
IWebHostEnvironmentMM7 J
envMMK N
)MMN O
{NN 	
ifOO 
(OO 
envOO 
.OO 
IsDevelopmentOO !
(OO! "
)OO" #
)OO# $
{PP 
appQQ 
.QQ %
UseDeveloperExceptionPageQQ -
(QQ- .
)QQ. /
;QQ/ 0
appRR 
.RR 

UseSwaggerRR 
(RR 
)RR  
;RR  !
appSS 
.SS 
UseSwaggerUISS  
(SS  !
cSS! "
=>SS# %
cSS& '
.SS' (
SwaggerEndpointSS( 7
(SS7 8
$strSS8 R
,SSR S
$strSST n
)SSn o
)SSo p
;SSp q
}TT 
appVV 
.VV 

UseRoutingVV 
(VV 
)VV 
;VV 
appXX 
.XX 
UseAuthenticationXX !
(XX! "
)XX" #
;XX# $
appYY 
.YY 
UseAuthorizationYY  
(YY  !
)YY! "
;YY" #
appZZ 
.ZZ 
UseCorsZZ 
(ZZ 
options[[ 
=>[[ 
options[[ "
.[[" #
WithOrigins[[# .
([[. /
$str[[/ F
)[[F G
.[[G H
AllowAnyMethod[[H V
([[V W
)[[W X
.[[X Y
AllowAnyHeader[[Y g
([[g h
)[[h i
)\\ 
;\\ 
app]] 
.]] 
UseEndpoints]] 
(]] 
	endpoints]] &
=>]]' )
{^^ 
	endpoints__ 
.__ 
MapControllers__ (
(__( )
)__) *
;__* +
}`` 
)`` 
;`` 
}aa 	
}bb 
}cc 