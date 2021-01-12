--======================================================================================
-- TEST DB
-- Inserts three fells into the test db (Great Gable, Helvellyn and Scafell Pike)
--======================================================================================
INSERT INTO regions (name) VALUES('Southern Lake District');
INSERT INTO regions (name) VALUES('Eastern Lake District');
INSERT INTO regions (name) VALUES('Central Lake District');

INSERT INTO os_maps (name) VALUES('OS Landranger 89');
INSERT INTO os_maps (name) VALUES('OS Landranger 90');
INSERT INTO os_maps (name) VALUES('OS Explorer OL6');
INSERT INTO os_maps (name) VALUES('OS Explorer OL5');

INSERT INTO classifications (name) VALUES('Birkett');
INSERT INTO classifications (name) VALUES('Marilyn');
INSERT INTO classifications (name) VALUES('Fellranger');

--=======================================================================================
-- great gable
--=======================================================================================
INSERT INTO fells VALUES ('NY211104', 899, 54.482, -3.219, 'Great Gable', 'NY215072', 425, 3);

INSERT INTO fells_classifications VALUES('NY211104', 1);
INSERT INTO fells_classifications VALUES('NY211104', 2);
INSERT INTO fells_classifications VALUES('NY211104', 3);

INSERT INTO fells_osmaps VALUES('NY211104', 1);
INSERT INTO fells_osmaps VALUES('NY211104', 2);
INSERT INTO fells_osmaps VALUES('NY211104', 3);
--=======================================================================================

--=======================================================================================
-- helvellyn
--=======================================================================================
INSERT INTO fells VALUES ('NY342151', 950, 54.527232, -3.016054, 'Helvellyn', 'NY215072', 712, 2);

INSERT INTO fells_classifications VALUES('NY342151', 2);

INSERT INTO fells_osmaps VALUES('NY342151', 2);
INSERT INTO fells_osmaps VALUES('NY342151', 4);
--=======================================================================================

--=======================================================================================
-- scafell pike
--=======================================================================================
INSERT INTO fells VALUES ('NY215072', 978, 54.454222, -3.211528, 'Scafell Pike', NULL, 912, 1);

INSERT INTO fells_classifications VALUES('NY215072', 1);
INSERT INTO fells_classifications VALUES('NY215072', 2);

INSERT INTO fells_osmaps VALUES('NY215072', 1);
INSERT INTO fells_osmaps VALUES('NY215072', 2);
INSERT INTO fells_osmaps VALUES('NY215072', 3);
--=======================================================================================





