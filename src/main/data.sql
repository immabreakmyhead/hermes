-- Inserting data into the program table
INSERT INTO program (id, name, description, benefits, costs, duration) VALUES
(1, 'Normal Plan', 'The Most Affordable and Value for Money Plan!', '10GB, 1000MINUTES OF CALLS', '16€', 18),
(2, 'Premium Plan', 'The Plan for the ones that are serious about their Phone', '30GB, 3000MINUTES OF CALLS', '24€', 24),
(3, 'Godly Plan', 'The Plan for the ones that want to forget what it means to not have Data', 'Unlimited Data, Unlimited Calls', '48€', 48);

-- Inserting data into the phoneNumber table
INSERT INTO phoneNumber (id, number, program_id, status) VALUES
(1, '6912345670', 1, 'ACTIVE'),
(2, '6912345671', 2, 'ACTIVE'),
(3, '6912345672', 3, 'ACTIVE'),
(4, '6912345673', 1, 'ACTIVE'),
(5, '6912345674', 2, 'ACTIVE'),
(6, '6912345675', 1, 'ACTIVE'),
(7, '6912345676', 3, 'ACTIVE'),
(8, '6912345677', 1, 'ACTIVE'),
(9, '6912345678', 2, 'INACTIVE'),
(10, '6912345679', 2, 'ACTIVE'),
(11, '6912345690', 2, 'INACTIVE'),
(12, '6912345691', 2, 'ACTIVE');

-- Inserting data into the bill table
INSERT INTO bill (id, phoneNumber, date, totalAmount, isPaid) VALUES
(1, '6912345670', '2023-06-01', 100.00, false),
(2, '6912345671', '2023-06-01', 120.00, false),
(3, '6912345672', '2023-06-01', 30.00, false),
(4, '6912345673', '2023-06-01', 40.00, false),
(5, '6912345674', '2023-06-01', 10.00, false),
(6, '6912345670', '2023-07-01', 100.00, false),
(7, '6912345671', '2023-07-01', 120.00, false),
(8, '6912345672', '2023-07-01', 30.00, false),
(9, '6912345673', '2023-07-01', 40.00, false),
(10, '6912345674', '2023-07-01', 10.00, false);

-- Inserting data into the call table
INSERT INTO "call" (callId, callerNumber, receiverNumber, duration, date) VALUES
(1, '6912345670', '6912345671', 120, '2023-06-01'),
(2, '6912345671', '6912345672', 180, '2023-06-03'),
(3, '6912345672', '6912345673', 90, '2023-06-05'),
(4, '6912345673', '6912345670', 150, '2023-06-09'),
(5, '6912345674', '6912345675', 200, '2023-06-11'),
(6, '6912345675', '6912345676', 110, '2023-06-21'),
(7, '6912345676', '6912345677', 140, '2023-06-23'),
(8, '6912345677', '6912345670', 170, '2023-06-25'),
(9, '6912345678', '6912345672', 80, '2023-06-25'),
(10, '6912345679', '6912345674', 190, '2023-06-30'),
(11, '6912345670', '6912345671', 130, '2023-07-01'),
(12, '6912345671', '6912345672', 170, '2023-07-03'),
(13, '6912345672', '6912345673', 110, '2023-07-07'),
(14, '6912345673', '6912345670', 140, '2023-07-12'),
(15, '6912345674', '6912345675', 180, '2023-07-14'),
(16, '6912345675', '6912345676', 100, '2023-07-16'),
(17, '6912345676', '6912345677', 150, '2023-07-23'),
(18, '6912345677', '6912345670', 160, '2023-07-27'),
(19, '6912345678', '6912345672', 90, '2023-07-28'),
(20, '6912345679', '6912345674', 210, '2023-07-30');
-- Inserting data into the user table
INSERT INTO public."user" (id, username, password, name, surname, email, role) VALUES
(1, 'jdoe', '5FA42F33A8515DDF9255A9C1155A4E96', 'John', 'Doe', 'jdoe@example.com', 'admin'),
(2, 'asmith', '25363A7C00F29F62ED23A8CA9F41C7C4', 'Alice', 'Smith', 'asmith@example.com', 'client'),
(3, 'bjones', '3071A517F7703A46E65B02250513B926', 'Bob', 'Jones', 'bjones@example.com', 'client'),
(4, 'cmiller', '7BC060C2BDA29BD599917DBB91D4BF0F', 'Carol', 'Miller', 'cmiller@example.com', 'seller'),
(5, 'dwilson', '9D62B4DF8111AD2D875995DD5E843562', 'David', 'Wilson', 'dwilson@example.com', 'client'),
(6, 'emartin', '3319EE130F6628B3C4A4A35ABEE18E25', 'Emma', 'Martin', 'emartin@example.com', 'client'),
(7, 'ffisher', 'B5CD5AD63F1EC7504AE11F4C0D3E2DD0', 'Frank', 'Fisher', 'ffisher@example.com', 'seller'),
(8, 'glane', '55EC06AAFFA415F56BC5DF6F6B4828D5', 'Grace', 'Lane', 'glane@example.com', 'client'),
(9, 'hthomas', '716A9F1D0884AA73DE4422B5E92BA7FC', 'Hank', 'Thomas', 'hthomas@example.com', 'seller'),
(10, 'ijames', 'F90A37B16F07EE4FB5C358AB4C5605AA', 'Ivy', 'James', 'ijames@example.com', 'client'),
(11, 'kjohnson', 'D1DE95CBC4A8D895A20DDFD703EE7E9E', 'Kate', 'Johnson', 'kjohnson@example.com', 'admin'),
(12, 'lwilliams', '762C69BA328C47C39F1026570FEC839D', 'Liam', 'Williams', 'lwilliams@example.com', 'client'),
(13, 'mmurphy', '35068302D9927A1FBB0A0ABE1D58759C', 'Mia', 'Murphy', 'mmurphy@example.com', 'client'),
(14, 'nrobinson', 'A1E472D2F28FA6EC77B659ED09C64A53', 'Noah', 'Robinson', 'nrobinson@example.com', 'seller'),
(15, 'oparker', '811DAEA85F0561020565CEECEF6F2631', 'Olivia', 'Parker', 'oparker@example.com', 'client'),
(16, 'pbrown', 'E8C8AEDA3249EACF62040128BDEB64F9', 'Paul', 'Brown', 'pbrown@example.com', 'seller'),
(17, 'qrussell', '953EABA875B3F0B03E779774F83BCB1F', 'Quinn', 'Russell', 'qrussell@example.com', 'client'),
(18, 'rlee', '3EBD6FB0C058660746D6F45DA10DAAD2', 'Rachel', 'Lee', 'rlee@example.com', 'client'),
(19, 'sstewart', '6086EBBA02BE58E8EE4F550060E7C63B', 'Sam', 'Stewart', 'sstewart@example.com', 'client'),
(20, 'tgreen', 'E0EEBA4C16ECD3C5450B756E3E01B7D7', 'Tina', 'Green', 'tgreen@example.com', 'seller');

-- Inserting data into the seller table
INSERT INTO seller (user_id, AM) VALUES
(4, 'AM001'),
(7, 'AM002'),
(9, 'AM003'),
(14, 'AM004'),
(16, 'AM005'),
(20, 'AM006');

-- Inserting data into the admin table
INSERT INTO admin (user_id) VALUES
(1),
(11);

INSERT INTO client (user_id, AM, phoneNumber) VALUES
(2, 'AM007', '6912345670'),
(3, 'AM008', '6912345671'),
(5, 'AM009', '6912345672'),
(6, 'AM010', '6912345673'),
(8, 'AM011', '6912345674'),
(10, 'AM012', '6912345675'),
(12, 'AM013', '6912345676'),
(13, 'AM014', '6912345677'),
(15, 'AM015', '6912345678'),
(17, 'AM016', '6912345679'),
(18, 'AM017', '6912345690'),
(19, 'AM018', '6912345691');

