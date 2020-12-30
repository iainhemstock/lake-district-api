INSERT INTO regions (id, name)
    VALUES
        (1, 'Eastern Lake District'),--1
        (2, 'Far Eastern Lake District'), --2
        (3, 'Central Lake District'),--3
        (4, 'Southern Lake District'),--4
        (5, 'Northern Lake District'),--5
        (6, 'North Western Lake District'),--6
        (7, 'Western Lake District'), --7
        (8, 'Scottish Highlands'),--8
        (9, 'Snowdonia');--9

INSERT INTO classificationEntities (id, name) VALUES (1, 'Wainwright');--1
INSERT INTO classificationEntities (id, name) VALUES (2, 'Hewitt');--2
INSERT INTO classificationEntities (id, name) VALUES (3, 'Marilyn');--3
INSERT INTO classificationEntities (id, name) VALUES (4, 'Nuttall');--4
INSERT INTO classificationEntities (id, name) VALUES (5, 'Country high point');--5
INSERT INTO classificationEntities (id, name) VALUES (6, 'Hardy');--6
INSERT INTO classificationEntities (id, name) VALUES (7, 'Current county top');--7
INSERT INTO classificationEntities (id, name) VALUES (8, 'Furth');--8
INSERT INTO classificationEntities (id, name) VALUES (9, 'Historic county top');--9
INSERT INTO classificationEntities (id, name) VALUES (10, 'Administrative county top');--10
INSERT INTO classificationEntities (id, name) VALUES (11, 'Birkett');--11
INSERT INTO classificationEntities (id, name) VALUES (12, 'HuMP');--12
INSERT INTO classificationEntities (id, name) VALUES (13, 'Simm');--13
INSERT INTO classificationEntities (id, name) VALUES (14, 'Synge');--14
INSERT INTO classificationEntities (id, name) VALUES (15, 'Fellranger');--15
INSERT INTO classificationEntities (id, name) VALUES (16, 'Tump');--16
INSERT INTO classificationEntities (id, name) VALUES (17, 'Dewey');--17

INSERT INTO os_maps (id, name) VALUES (1, 'OS Landranger 89');--1
INSERT INTO os_maps (id, name) VALUES (2, 'OS Landranger 90');--2
INSERT INTO os_maps (id, name) VALUES (3, 'OS Landranger 96');--3
INSERT INTO os_maps (id, name) VALUES (4, 'OS Landranger 97');--4
INSERT INTO os_maps (id, name) VALUES (5, 'OS Explorer OL4');--5
INSERT INTO os_maps (id, name) VALUES (6, 'OS Explorer OL5');--6
INSERT INTO os_maps (id, name) VALUES (7, 'OS Explorer OL6');--7
INSERT INTO os_maps (id, name) VALUES (8, 'OS Explorer OL7');--8

INSERT INTO fellEntities (id, name, height_meters, region_id, prominence_meters, parent_peak_id, latitude, longitude, os_map_ref)
    VALUES
        (1, 'Ben Nevis', 1344, 8, 1344, 1, 0, 0, ''),
        (2, 'Snowdon', 1085, 9, 1039, 1, 0, 0, ''),
        (3, 'Scafell Pike', 978, 4, 912, 2, 54.454222, -3.211528, 'NY215072'),
        (4, 'Scafell', 964, 4, 133, 3, 54.448, -3.225, 'NY206064'),
        (5, 'Helvellyn', 950, 1, 712, 3, 54.527232, -3.016054, 'NY342151'),
        (6, 'Skiddaw', 931, 5, 709, 6, 54.647, -3.146, 'NY260290'),
        (7, 'Great Gable', 899, 7, 425, 3, 54.482, -3.219, 'NY211104'),
        (8, 'Grasmoor', 852, 6, 519, 3, 54.57115, -3.27918, 'NY174203'),
        (9, 'High Street', 828, 2, 373, 5, 54.492, -2.865, 'NY440110'),
        (10, 'High Raise (Langdale)', 762, 3, 283, 3, 54.476, -3.113, 'NY280095'),
        (11, 'Pike Of Stickle', 709, 3, 54, 10, 54.45586, -3.12287, 'NY273073'),
        (12, 'Fleetwith Pike', 648, 7, 117, 7, 54.51594, -3.22956, 'NY205141'),
        (13, 'Sail', 773, 6, 32, 1, 54.571, -3.242, 'NY198204');

-- SCAFELL PIKE
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (3, 1);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (3, 2);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (3, 3);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (3, 4);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (3, 5);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (3, 7);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (3, 8);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (3, 9);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (3, 10);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (3, 11);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (3, 12);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (3, 13);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (3, 14);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (3, 15);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (3, 16);

INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (3, 1);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (3, 2);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (3, 7);

-- SCAFELL
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (4, 1);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (4, 2);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (4, 4);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (4, 8);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (4, 11);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (4, 12);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (4, 13);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (4, 14);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (4, 15);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (4, 16);

INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (4, 1);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (4, 2);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (4, 7);

-- HELVELLYN
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (5, 1);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (5, 2);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (5, 3);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (5, 4);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (5, 8);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (5, 9);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (5, 11);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (5, 12);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (5, 13);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (5, 14);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (5, 15);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (5, 16);

INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (5, 2);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (5, 6);

-- SKIDDAW
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (6, 1);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (6, 2);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (6, 3);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (6, 4);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (6, 8);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (6, 11);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (6, 12);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (6, 13);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (6, 14);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (6, 15);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (6, 16);

INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (6, 1);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (6, 2);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (6, 5);

--GREAT GABLE
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (7, 1);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (7, 2);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (7, 3);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (7, 4);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (7, 11);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (7, 12);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (7, 13);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (7, 14);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (7, 15);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (7, 16);

INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (7, 1);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (7, 2);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (7, 7);

-- GRASMOOR
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (8, 1);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (8, 2);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (8, 3);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (8, 4);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (8, 11);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (8, 12);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (8, 13);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (8, 14);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (8, 15);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (8, 16);

INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (8, 1);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (8, 2);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (8, 5);

-- HIGH STREET
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (9, 1);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (9, 2);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (9, 3);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (9, 4);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (9, 11);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (9, 12);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (9, 13);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (9, 14);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (9, 15);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (9, 16);

INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (9, 2);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (9, 6);

-- HIGH RAISE (LANGDALE)
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (10, 1);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (10, 2);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (10, 3);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (10, 4);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (10, 11);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (10, 12);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (10, 13);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (10, 14);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (10, 15);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (10, 16);

INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (10, 1);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (10, 2);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (10, 7);

-- PIKE OF STICKLE
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (11, 1);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (11, 2);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (11, 4);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (11, 11);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (11, 13);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (11, 14);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (11, 15);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (11, 16);

INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (11, 1);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (11, 2);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (11, 7);

-- FLEETWITH PIKE
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (12, 1);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (12, 2);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (12, 4);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (12, 11);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (12, 12);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (12, 13);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (12, 14);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (12, 15);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (12, 16);

INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (12, 1);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (12, 2);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (12, 5);

-- SAIL
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (13, 1);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (13, 2);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (13, 4);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (13, 11);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (13, 13);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (13, 14);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (13, 15);
INSERT INTO fells_classifications (fell_id, classification_id) VALUES (13, 16);

INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (13, 1);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (13, 2);
INSERT INTO fells_osmaps (fell_id, os_map_id) VALUES (13, 5);
