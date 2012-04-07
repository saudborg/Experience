-- ITEM
CREATE TABLE ITEM
( 
	id SERIAL NOT NULL,
	name VARCHAR(20) NOT NULL,
	price DECIMAL (10,2) NOT NULL,
	distance DECIMAL(10,2) NOT NULL,
	validity INTEGER NOT NULL,
	serviceProviderRating INTEGER NOT NULL,
	isOnSale BOOLEAN NOT NULL,
	CONSTRAINT id_item_pk PRIMARY KEY (id)
);

-- CONSUMER
CREATE TABLE CONSUMER
( 
	id SERIAL NOT NULL,
	name VARCHAR(20) NOT NULL,
	paramA DECIMAL(5,5) NOT NULL,
	paramB DECIMAL(5,5) NOT NULL,
	paramC DECIMAL(5,5) NOT NULL,
	paramD DECIMAL(5,5) NOT NULL,
	paramE DECIMAL(5,5) NOT NULL,
	probability DECIMAL(5,5) NOT NULL,
	CONSTRAINT id_consumer_pk PRIMARY KEY (id)
);

-- CONSUMER PURCHASED ITEMS
create table consumer_purchased_item
(
	id serial not null,
	id_consumer integer not null,
	id_item integer not null,
	purchased_date timestamp not null,
	constraint id_consumer_purchased_item_pk
	PRIMARY KEY (id,id_consumer,id_item,purchased_date)
);

-- CONSUMER PREFERENCE
create table consumer_preference
(
	id serial not null,
	id_consumer integer not null,
	id_item_i integer not null,
	id_item_j integer not null,
	id_item_prefered varchar(20) not null,
	constraint id_consumer_preference_pk
	PRIMARY KEY (id, id_consumer, id_item_i, id_item_j, id_item_prefered)
);

