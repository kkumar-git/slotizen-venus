--liquibase formatted sql
--changeset kkumar:insert_business_profile_jewel

--Kottyam, Kerala, India
insert
    into
    public.business_profile
(business_id,
    business_name,            
    logo_url,
    business_type,
    description,
    address,
    city,
    state,
    zip_code,
    phone,
    website,
    timezone,
    slug,
    active,
    competition_level,
    completed,
    created_at)
values(nextval('business_profile_business_id_seq'::regclass), 'Jewel - Kottayam',
    'http://localhost:8081/api/static/user-image/user-image_16_20251005_145725.jpg',
    'therapy-and-rehabilitation-centre',
    'Jewel Autism Centre is a multi-speciality therapy clinic founded in 2008 specially designed to bridge the gaps in the developmental history of a child. Our centre offers the best autism treatment in India',
    'Cheriyapally Hospital Buildings, Ooppoottil Kavala',
    'Chalukunnu, Kottayam',
    'Kerala',
    '686001',
    '+9197454 51747',
    'https://www.jewelautismcentre.com/',
    'Asia/Kolkata',
    'jewel-autism-centre-india',
    true,
    5,
    true,
    CURRENT_TIMESTAMP);

--Kakkanad, Kerala, India
    insert
    into
    public.business_profile
(business_id,
    business_name,            
    logo_url,
    business_type,
    description,
    address,
    city,
    state,
    zip_code,
    phone,
    website,
    timezone,
    slug,
    active,
    competition_level,
    completed,
    created_at)
values(nextval('business_profile_business_id_seq'::regclass), 'Jewel - Kakkanad',
    'http://localhost:8081/api/static/user-image/user-image_16_20251005_145725.jpg',
    'therapy-and-rehabilitation-centre',
    'Jewel Autism Centre is a multi-speciality therapy clinic founded in 2008 specially designed to bridge the gaps in the developmental history of a child. Our centre offers the best autism treatment in India',
    'Chandrasekharan Nair Road, Near Kendriya Bhavan',
    'CSEZ (P.O), Kakkanad',
    'Kerala',
    '682037',
    '+9197454 51747',
    'https://www.jewelautismcentre.com/',
    'Asia/Kolkata',
    'jewel-autism-centre',
    true,
    5,
    true,
    CURRENT_TIMESTAMP);

--Dubai, United Arab Emirates
    insert
    into
    public.business_profile
(business_id,
    business_name,            
    logo_url,
    business_type,
    description,
    address,
    city,
    state,
    zip_code,
    phone,
    website,
    timezone,
    slug,
    active,
    competition_level,
    completed,
    created_at)
values(nextval('business_profile_business_id_seq'::regclass), 'Jewel - Dubai',
    'http://localhost:8081/api/static/user-image/user-image_16_20251005_145725.jpg',
    'therapy-and-rehabilitation-centre',
    'Jewel Autism Centre is a multi-speciality therapy clinic founded in 2008 specially designed to bridge the gaps in the developmental history of a child. Our centre offers the best autism treatment in Dubai',
    'Room No. 106 1st Floor',
    'Galadari Mazda Showroom Sheik Zayed Road',
    'Dubai United Arab Emirates',
    '0',
    '+9197454 51747',
    'https://www.jewelautismcentre.com/',
    'Asia/Dubai',
    'jewel-autism-centre-dubai',
    true,
    5,
    true,
    CURRENT_TIMESTAMP);
