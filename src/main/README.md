ΠΡΟΓΡΑΜΜΑΤΙΣΜΟΣ ΣΤΟ ΔΙΑΔΙΚΤΥΟ ΚΑΙ ΣΤΟΝ ΠΑΓΚΟΣΜΙΟ ΙΣΤΟ – ΕΡΓΑΣΙΑ

### STUDENTS
NAME: HERMES
APP: διαδικτυακής εφαρμογής έκδοσης λογαριασμών και διαχείρισης πελατών κινητής τηλεφωνίας.
TECHNOLOGIES: servlets και jsp.

   Class diagram
   =============
   draw.io --> 

   Class User
   =============
   ### Attributes
      1. username ( String ) ✓
      2. name ( String ) ✓
      3. surname ( String ) ✓
      4. email ( String ) ✓
      5. role ( CLIENT, SELLER, ADMIN ) ✓
      6. usersCounter ( static incrementing int ) ✓
   ### Methods
      - login() ✓
      - register() ✓
      - logout() ✓
      - getUsername() ✓
      - setUsername() ✓
      - getName() ✓
      - setName() ✓
      - getSurname() ✓
      - setSurname() ✓
      - getEmail() ✓
      - setEmail() ✓
      - getRole() ✓
      - setRole() ✓
      - getUserCounter() ✓

   Class Client --> Subclass of User
   ============
   ### Attributes 
      1.  AM ( constant unique int ) ✓
      2.  phoneNumber ( PhoneNumber ) ✓
   ### Methods
      - showBill() ✓
      - showHistory() ✓
      - payBill() ✓
      - getAM() ✓
      - getPhoneNumber() ✓
      - setPhoneNumber() ✓

   Class Seller --> Subclass of User
   ============
   ### Attributes
      1. AM ( constant unique int )
   ### Methods
      - addNewClient() ✓
      - changeClientProgram() ✓
      - issueClientBill() ✓
      - getAM() ✓

   Class Admin --> Subclass of User
   ============
   ### Attributes
      NO EXTRA ATTRIBUTES
   ### Methods
      - createClient() ✓
      - editClient() ✓
      - deleteClient() ✓
      - createSeller() ✓
      - editSeller() ✓
      - deleteSeller() ✓
      - createProgram() ✓
      - editProgram() ✓
      - deleteProgram() ✓

   Class PhoneNumber
   =================
   ### Attributes
      1. number ( int ) ✓
      2. program ( Program ) ✓
      3. status ( String ) ✓
   ### Methods
      - getPhone() ✓
      - setPhone() ✓
      - getProgram() ✓
      - setProgram() ✓
      - deactivate() ✓
      - activate() ✓
      - displayPhoneNumberInfo() ✓

   Class Program
   =============
   ### Attributes
      1. name ( String ) ✓
      2. description ( String ) ✓
      3. benefits ( List<String> ) ✓
      4. costs ( List<String> ) ✓
      5. duration ( int ) ✓
   ### Methods:
      - displayProgramInfo() ✓
   
   Call Class
   ==========
   ### Attributes:
      1. callID ( final int ) ✓
      2. callType ( final CallType ) ✓
      3. callerNumber ( final PhoneNumber ) ✓
      4. receiverNumber ( final PhoneNumber ) ✓
      5. duration ( int ) ✓
   ### Methods:
      - getCallID() ✓
      - getCallType() ✓
      - getCallerNumber() ✓
      - getReceiverNumber() ✓
      - getDuration() ✓
      - setDuration() ✓
      - displayCallInfo() ✓

   Call Bill
   =========
   ### Attributes:
      1. billID ( final int ) ✓
      2. phoneNumber ( PhoneNumber ) ✓
      3. month ( int ) ✓
      4. charges ( List<Call> ) ✓
      5. totalAmount ( double ) ✓
      6. isPaid ( boolean ) ✓
   ### Methods:
      - getBillID() ✓
      - getPhoneNumber() ✓
      - setPhoneNumber() ✓
      - getMonth() ✓
      - setMonth() ✓
      - getCharges() ✓
      - setCharges() ✓
      - getTotalAmount() ✓
      - getBillStatus() ✓
      - displayCallInfo() ✓

Η παράδοση της εργασίας θα γίνεται ως εξής:
• Τμηματικά, μέσω των ενδιάμεσων ασκήσεων που θα ανακοινώνονται και θα
παραδίδονται μέσω του eclass. Συνεπώς οι ομάδες εργασίας θα δημιουργηθούν από την
αρχική άσκηση και θα παραμείνουν οι ίδιες μέχρι το τέλος του μαθήματος.
• Η απαλλακτική εργασία θα περιλαμβάνει όλες τις ενδιάμεσες ασκήσεις και τα επιπλέον
βήματα της υλοποίησης που απαιτούνται. Η τελική εργασία θα παραδοθεί επίσης μέσω
του eclass και θα ανακοινωθεί πρόγραμμα εξέτασης. Η εξέταση θα περιλαμβάνει και όλα
τα ενδιάμεσα παραδοτέα (Ασκήσεις).