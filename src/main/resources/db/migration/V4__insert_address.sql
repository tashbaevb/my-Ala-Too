insert into address_type(id, title_kg, title_ru, title_en) values(
                                                                     1,
                                                                     'мамлекет',
                                                                     'страна',
                                                                     'country'
                                                                 ),
                                                                 (
                                                                     2,
                                                                     'шаар',
                                                                     'город',
                                                                     'city'
                                                                 ),
                                                                 (
                                                                     3,
                                                                     'область',
                                                                     'область',
                                                                     'oblast'
                                                                 );

insert into address (p_id, address_type_id, title_kg, title_ru, title_en)
values (null, 1, 'Кыргызстан', 'Кыргызстан', 'Kyrgyzstan'),
       (1, 2, 'Бишкек', 'Бишкек', 'Bishkek');
