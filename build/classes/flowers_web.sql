DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS occasion;
DROP DATABASE IF EXISTS flowes_web;

CREATE DATABASE IF NOT EXISTS flowers_web;

USE flowers_web;

/*Table structure for table occasion */	
CREATE TABLE occasion (
    occasion_id INTEGER AUTO_INCREMENT ,
    occasion VARCHAR(30) NOT NULL,
    PRIMARY KEY (occasion_id)
);


/*Table structure for table products */
CREATE TABLE products (
    product_id INTEGER AUTO_INCREMENT NOT NULL,
    product_name VARCHAR(45),
    product_description VARCHAR(256),
    main_color VARCHAR(50),
    available VARCHAR(1),
    occasion_id INTEGER,
    environment VARCHAR(10),
    dimensions VARCHAR(20),
    price DOUBLE NOT NULL,
    picture VARCHAR(256) DEFAULT NULL,
    PRIMARY KEY (product_id),
    CONSTRAINT FK_ProductOccasion FOREIGN KEY (occasion_id)
        REFERENCES occasion (occasion_id)
);

/* Data for the table occasion */
INSERT INTO occasion VALUES 
(1, 'Birthday'),
(2, 'Valentines day'),
(3, 'Mothers day'),
(4, 'Thank you'),
(5, 'Congratulations'),
(6, 'Funeral Arrangements');

/* Data for the table products */
INSERT INTO products VALUES 
(1001,
'Special Bouquet',
'Colorful Bouquet with 40 roses.', 'Orange','Y',
1,'Indoor', '50x30x20',
100.00,
'images/special_bouquet.jpg');

INSERT INTO products VALUES 
(1002,
'Heart Flower',
'Beautiful arrangement of red roses in a heart shape. In perfect harmony, these roses receive a black paraffin heart, with details on black satin ribbon.',
'Red', 'Y',2,'Indoor','40x20x10',
80.00,
'images/heart_flower.jpg');

INSERT INTO products VALUES 
(1003,
'Autumn Flower',
'Coloring the day. A beautiful potted arrangement consisting of six orange leaves amid dracena leaves.',
'Orange', 'N',3,'Outdoor','30x20x10',
60.00,
'images/autumn_flower.jpg');

INSERT INTO products VALUES 
(1004,
'Life',
'Ten units of Lindor Swiss chocolate, accompanied by a sophisticated Colombian red rose.',
'Red', 'Y',4,'Indoor','20x10x5',
35.00,
'images/life.jpg');

INSERT INTO products VALUES 
(1005,
'Flower Basket',
'Delicate, light and dazzling as a ballerina. A beautiful arrangement with lisianthus and roses, in pink tones.',
'Pink', 'N',3,'Outdoor','50x30x20',
95.00,
'images/flower_basket.jpg');

INSERT INTO products VALUES 
(1006,
'A Dream',
'A beautiful bouquet with super cheerful colors, composed of roses and lisianthus, accompanies a cute bear in soft fabric.',
'Yellow', 'Y',2,'Indoor','40x20x10',
75.00,
'images/a_dream.jpg');

INSERT INTO products VALUES 
(1007,
'Peace',
'Lilies and large-headed roses are nestled amongst freesias and lisianthus along with eryngium and foliage in this circular wreath.',
'White', 'Y',6,'Outdoor','60x30x20',
150.00,
'images/peace.jpg');

INSERT INTO products VALUES 
(1008,
'Daisy Queen',
'The queen of the field! Two small vases of daisies, in a rustic and charming wooden crate.',
'White', 'Y',5,'Outdoor','40x20x10',
150.00,
'images/daisy_queen.jpg');

INSERT INTO products VALUES 
(1009,
'Smile',
'Vividness of colors in this mix of roses in shades of pink, orange, lilac, red and brown, next to the green leaves in a glass vase lined with reed.',
'Orange', 'N',1,'Outdoor','30x20x10',
95.00,
'images/smile.jpg');

INSERT INTO products VALUES 
(1010,
'Principal Bouquet',
'Festive and colorful mix of Colombian roses, national roses, mini roses, lilies, ageratum, alstroeméria, lisianthus, hydrangeas and foliage form this super cheerful bouquet.',
'Purple', 'N',1,'Indoor','50x40x20',
200.00,
'images/principal_bouquet.jpg');

SELECT product_id, product_name, product_description, occasion,price  FROM
    products 
        INNER JOIN
    occasion ON products.occasion_id = occasion.occasion_id WHERE occasion = 'Birthday';
   
SELECT * FROM products; 

UPDATE products SET product_name=?, product_description=?, main_color=?, available=?, occasion_id=?, environment=?, dimensions=?, 
							price=?, picture=? WHERE product_id=?;

SELECT * FROM occasion; 

SELECT product_id, product_name, product_description, occasion,price 
FROM products INNER JOIN occasion ON products.occasion_id = occasion.occasion_id WHERE occasion LIKE '%Birthday%';

SELECT *
FROM products INNER JOIN occasion ON products.occasion_id = occasion.occasion_id WHERE occasion LIKE '%Birthday%';
SELECT * FROM occasion ORDER BY occasion;
