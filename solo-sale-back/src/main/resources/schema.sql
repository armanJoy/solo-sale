--
-- PostgreSQL database dump
--

-- Dumped from database version 14.13 (Ubuntu 14.13-1.pgdg22.04+1)
-- Dumped by pg_dump version 17.0 (Ubuntu 17.0-1.pgdg22.04+1)

-- Started on 2024-11-07 22:10:16 +06

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3566 (class 1262 OID 21546)
-- Name: inv_db; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE inv_db_test WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';


\connect inv_db_test

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA public;


--
-- TOC entry 3567 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 224 (class 1259 OID 21683)
-- Name: brand; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.brand (
    id bigint NOT NULL,
    created_time timestamp(6) without time zone NOT NULL,
    is_active boolean,
    updated_time timestamp(6) without time zone,
    name character varying(50) NOT NULL,
    created_by_id bigint,
    updated_by_id bigint
);


--
-- TOC entry 223 (class 1259 OID 21682)
-- Name: brand_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.brand_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3568 (class 0 OID 0)
-- Dependencies: 223
-- Name: brand_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.brand_id_seq OWNED BY public.brand.id;


--
-- TOC entry 210 (class 1259 OID 21548)
-- Name: employee; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.employee (
    id bigint NOT NULL,
    created_time timestamp(6) without time zone NOT NULL,
    is_active boolean,
    updated_time timestamp(6) without time zone,
    employee_id character varying(50) NOT NULL,
    joining_date date NOT NULL,
    name character varying(80) NOT NULL,
    phone_number character varying(255) NOT NULL,
    user_id bigint,
    created_by_id bigint,
    updated_by_id bigint,
    designation_id bigint
);


--
-- TOC entry 212 (class 1259 OID 21555)
-- Name: employee_designation; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.employee_designation (
    id bigint NOT NULL,
    created_time timestamp(6) without time zone NOT NULL,
    is_active boolean,
    updated_time timestamp(6) without time zone,
    name character varying(50) NOT NULL,
    created_by_id bigint,
    updated_by_id bigint
);


--
-- TOC entry 211 (class 1259 OID 21554)
-- Name: employee_designation_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.employee_designation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3569 (class 0 OID 0)
-- Dependencies: 211
-- Name: employee_designation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.employee_designation_id_seq OWNED BY public.employee_designation.id;


--
-- TOC entry 209 (class 1259 OID 21547)
-- Name: employee_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3570 (class 0 OID 0)
-- Dependencies: 209
-- Name: employee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.employee_id_seq OWNED BY public.employee.id;


--
-- TOC entry 214 (class 1259 OID 21562)
-- Name: login_history; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.login_history (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    user_agent character varying(255),
    userip character varying(255),
    login_by bigint
);


--
-- TOC entry 213 (class 1259 OID 21561)
-- Name: login_history_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.login_history_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3571 (class 0 OID 0)
-- Dependencies: 213
-- Name: login_history_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.login_history_id_seq OWNED BY public.login_history.id;


--
-- TOC entry 216 (class 1259 OID 21571)
-- Name: otp_entries; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.otp_entries (
    id bigint NOT NULL,
    is_valid boolean,
    one_time_password character varying(255),
    otp_requested_time timestamp(6) with time zone,
    updated_at timestamp(6) with time zone,
    user_email character varying(255)
);


--
-- TOC entry 215 (class 1259 OID 21570)
-- Name: otp_entries_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.otp_entries_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3572 (class 0 OID 0)
-- Dependencies: 215
-- Name: otp_entries_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.otp_entries_id_seq OWNED BY public.otp_entries.id;


--
-- TOC entry 226 (class 1259 OID 21690)
-- Name: product; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.product (
    id bigint NOT NULL,
    created_time timestamp(6) without time zone NOT NULL,
    is_active boolean,
    updated_time timestamp(6) without time zone,
    mrp double precision,
    name character varying(255) NOT NULL,
    selling_price double precision,
    stock integer,
    created_by_id bigint,
    updated_by_id bigint,
    brand_id bigint,
    category_id bigint
);


--
-- TOC entry 228 (class 1259 OID 21697)
-- Name: product_category; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.product_category (
    id bigint NOT NULL,
    created_time timestamp(6) without time zone NOT NULL,
    is_active boolean,
    updated_time timestamp(6) without time zone,
    name character varying(50) NOT NULL,
    created_by_id bigint,
    updated_by_id bigint
);


--
-- TOC entry 227 (class 1259 OID 21696)
-- Name: product_category_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.product_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3573 (class 0 OID 0)
-- Dependencies: 227
-- Name: product_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.product_category_id_seq OWNED BY public.product_category.id;


--
-- TOC entry 225 (class 1259 OID 21689)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3574 (class 0 OID 0)
-- Dependencies: 225
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- TOC entry 230 (class 1259 OID 21978)
-- Name: purchase; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.purchase (
    id bigint NOT NULL,
    created_time timestamp(6) without time zone NOT NULL,
    is_active boolean,
    updated_time timestamp(6) without time zone,
    purchase_date date,
    total_price double precision,
    vendor character varying(100),
    created_by_id bigint,
    updated_by_id bigint
);


--
-- TOC entry 229 (class 1259 OID 21977)
-- Name: purchase_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.purchase_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3575 (class 0 OID 0)
-- Dependencies: 229
-- Name: purchase_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.purchase_id_seq OWNED BY public.purchase.id;


--
-- TOC entry 231 (class 1259 OID 21984)
-- Name: purchase_purchased_products; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.purchase_purchased_products (
    purchase_id bigint NOT NULL,
    purchased_products_id bigint NOT NULL
);


--
-- TOC entry 233 (class 1259 OID 21990)
-- Name: purchased_product; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.purchased_product (
    id bigint NOT NULL,
    created_time timestamp(6) without time zone NOT NULL,
    is_active boolean,
    updated_time timestamp(6) without time zone,
    price double precision,
    quantity integer,
    created_by_id bigint,
    updated_by_id bigint,
    product_id bigint,
    purchase_id bigint
);


--
-- TOC entry 232 (class 1259 OID 21989)
-- Name: purchased_product_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.purchased_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3576 (class 0 OID 0)
-- Dependencies: 232
-- Name: purchased_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.purchased_product_id_seq OWNED BY public.purchased_product.id;


--
-- TOC entry 218 (class 1259 OID 21580)
-- Name: refresh_token; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.refresh_token (
    id bigint NOT NULL,
    expire_time timestamp(6) with time zone NOT NULL,
    refresh_token character varying(255),
    user_id bigint
);


--
-- TOC entry 217 (class 1259 OID 21579)
-- Name: refresh_token_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.refresh_token_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3577 (class 0 OID 0)
-- Dependencies: 217
-- Name: refresh_token_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.refresh_token_id_seq OWNED BY public.refresh_token.id;


--
-- TOC entry 220 (class 1259 OID 21587)
-- Name: roles; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.roles (
    id bigint NOT NULL,
    created_time timestamp(6) without time zone NOT NULL,
    is_active boolean,
    updated_time timestamp(6) without time zone,
    name character varying(20) NOT NULL,
    created_by_id bigint,
    updated_by_id bigint
);


--
-- TOC entry 219 (class 1259 OID 21586)
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3578 (class 0 OID 0)
-- Dependencies: 219
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;


--
-- TOC entry 235 (class 1259 OID 21997)
-- Name: sale; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sale (
    id bigint NOT NULL,
    created_time timestamp(6) without time zone NOT NULL,
    is_active boolean,
    updated_time timestamp(6) without time zone,
    customer_address character varying(150),
    customer_name character varying(100),
    customer_phone character varying(14),
    delivered boolean NOT NULL,
    delivery_charge double precision,
    discount_percentage double precision,
    paid boolean NOT NULL,
    sale_date date,
    total_price double precision,
    created_by_id bigint,
    updated_by_id bigint,
    total_product_price double precision
);


--
-- TOC entry 234 (class 1259 OID 21996)
-- Name: sale_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.sale_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3579 (class 0 OID 0)
-- Dependencies: 234
-- Name: sale_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.sale_id_seq OWNED BY public.sale.id;


--
-- TOC entry 236 (class 1259 OID 22003)
-- Name: sale_sold_products; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sale_sold_products (
    sale_id bigint NOT NULL,
    sold_products_id bigint NOT NULL
);


--
-- TOC entry 238 (class 1259 OID 22009)
-- Name: sold_product; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sold_product (
    id bigint NOT NULL,
    created_time timestamp(6) without time zone NOT NULL,
    is_active boolean,
    updated_time timestamp(6) without time zone,
    mrp double precision,
    quantity integer,
    selling_price double precision,
    created_by_id bigint,
    updated_by_id bigint,
    product_id bigint,
    sale_id bigint
);


--
-- TOC entry 237 (class 1259 OID 22008)
-- Name: sold_product_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.sold_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3580 (class 0 OID 0)
-- Dependencies: 237
-- Name: sold_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.sold_product_id_seq OWNED BY public.sold_product.id;


--
-- TOC entry 222 (class 1259 OID 21594)
-- Name: t_users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.t_users (
    id bigint NOT NULL,
    created_time timestamp(6) without time zone NOT NULL,
    is_active boolean,
    updated_time timestamp(6) without time zone,
    email character varying(80),
    full_name character varying(80),
    password character varying(255) NOT NULL,
    username character varying(50) NOT NULL,
    created_by_id bigint,
    updated_by_id bigint,
    role_id bigint
);


--
-- TOC entry 221 (class 1259 OID 21593)
-- Name: t_users_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.t_users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3581 (class 0 OID 0)
-- Dependencies: 221
-- Name: t_users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.t_users_id_seq OWNED BY public.t_users.id;


--
-- TOC entry 3287 (class 2604 OID 21686)
-- Name: brand id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.brand ALTER COLUMN id SET DEFAULT nextval('public.brand_id_seq'::regclass);


--
-- TOC entry 3280 (class 2604 OID 21551)
-- Name: employee id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employee ALTER COLUMN id SET DEFAULT nextval('public.employee_id_seq'::regclass);


--
-- TOC entry 3281 (class 2604 OID 21558)
-- Name: employee_designation id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employee_designation ALTER COLUMN id SET DEFAULT nextval('public.employee_designation_id_seq'::regclass);


--
-- TOC entry 3282 (class 2604 OID 21565)
-- Name: login_history id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.login_history ALTER COLUMN id SET DEFAULT nextval('public.login_history_id_seq'::regclass);


--
-- TOC entry 3283 (class 2604 OID 21574)
-- Name: otp_entries id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.otp_entries ALTER COLUMN id SET DEFAULT nextval('public.otp_entries_id_seq'::regclass);


--
-- TOC entry 3288 (class 2604 OID 21693)
-- Name: product id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- TOC entry 3289 (class 2604 OID 21700)
-- Name: product_category id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_category ALTER COLUMN id SET DEFAULT nextval('public.product_category_id_seq'::regclass);


--
-- TOC entry 3290 (class 2604 OID 21981)
-- Name: purchase id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.purchase ALTER COLUMN id SET DEFAULT nextval('public.purchase_id_seq'::regclass);


--
-- TOC entry 3291 (class 2604 OID 21993)
-- Name: purchased_product id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.purchased_product ALTER COLUMN id SET DEFAULT nextval('public.purchased_product_id_seq'::regclass);


--
-- TOC entry 3284 (class 2604 OID 21583)
-- Name: refresh_token id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.refresh_token ALTER COLUMN id SET DEFAULT nextval('public.refresh_token_id_seq'::regclass);


--
-- TOC entry 3285 (class 2604 OID 21590)
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);


--
-- TOC entry 3292 (class 2604 OID 22000)
-- Name: sale id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sale ALTER COLUMN id SET DEFAULT nextval('public.sale_id_seq'::regclass);


--
-- TOC entry 3293 (class 2604 OID 22012)
-- Name: sold_product id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sold_product ALTER COLUMN id SET DEFAULT nextval('public.sold_product_id_seq'::regclass);


--
-- TOC entry 3286 (class 2604 OID 21597)
-- Name: t_users id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.t_users ALTER COLUMN id SET DEFAULT nextval('public.t_users_id_seq'::regclass);


--
-- TOC entry 3546 (class 0 OID 21683)
-- Dependencies: 224
-- Data for Name: brand; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.brand VALUES (1, '2024-11-06 22:38:10.730758', true, '2024-11-06 22:38:10.730803', 'Kiam', 1, NULL);
INSERT INTO public.brand VALUES (2, '2024-11-06 22:38:20.306798', true, '2024-11-06 22:38:20.306818', 'RFL', 1, NULL);
INSERT INTO public.brand VALUES (3, '2024-11-06 22:38:26.674655', true, '2024-11-06 22:38:26.674673', 'Nova', 1, NULL);


--
-- TOC entry 3532 (class 0 OID 21548)
-- Dependencies: 210
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.employee VALUES (1, '2024-11-06 15:46:46.039671', true, '2024-11-06 15:46:46.039715', 'EG-0001', '2020-11-06', 'Reza Ahmed', '01738014685', 2, 1, NULL, 1);


--
-- TOC entry 3534 (class 0 OID 21555)
-- Dependencies: 212
-- Data for Name: employee_designation; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.employee_designation VALUES (1, '2024-11-06 15:45:20.871501', true, '2024-11-06 15:45:20.871544', 'Officer', 1, NULL);


--
-- TOC entry 3536 (class 0 OID 21562)
-- Dependencies: 214
-- Data for Name: login_history; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.login_history VALUES (1, '2024-11-06 15:43:51.653772', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (2, '2024-11-06 21:57:03.197675', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (3, '2024-11-07 00:56:07.880269', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (4, '2024-11-07 02:05:08.857861', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (5, '2024-11-07 10:59:02.997505', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (6, '2024-11-07 11:10:13.927239', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (7, '2024-11-07 11:58:17.484245', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (8, '2024-11-07 11:59:52.11008', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (9, '2024-11-07 12:01:03.051222', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (10, '2024-11-07 12:01:05.124016', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (11, '2024-11-07 12:31:48.702535', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (12, '2024-11-07 12:31:50.363222', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (13, '2024-11-07 12:31:57.476015', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (14, '2024-11-07 12:37:31.408521', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (15, '2024-11-07 12:40:37.504923', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (16, '2024-11-07 12:55:35.971109', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (17, '2024-11-07 12:56:00.022649', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (18, '2024-11-07 12:56:03.961225', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (19, '2024-11-07 12:57:00.603735', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (20, '2024-11-07 12:57:19.271869', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (21, '2024-11-07 13:10:14.00695', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (22, '2024-11-07 14:53:20.112612', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (23, '2024-11-07 15:24:31.91411', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (24, '2024-11-07 16:42:56.921986', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (25, '2024-11-07 17:19:05.96564', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (26, '2024-11-07 18:27:02.61334', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (27, '2024-11-07 20:47:37.407994', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (28, '2024-11-07 20:48:04.30679', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);
INSERT INTO public.login_history VALUES (29, '2024-11-07 20:57:06.971463', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:132.0) Gecko/20100101 Firefox/132.0', '127.0.0.1', 1);


--
-- TOC entry 3538 (class 0 OID 21571)
-- Dependencies: 216
-- Data for Name: otp_entries; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3548 (class 0 OID 21690)
-- Dependencies: 226
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.product VALUES (1, '2024-11-07 01:02:47.523478', true, '2024-11-07 21:12:17.583653', 120, 'Gas Stove 001', 100, 16, 1, 1, 1, 1);
INSERT INTO public.product VALUES (2, '2024-11-07 01:03:02.077377', true, '2024-11-07 22:03:04.848747', 120, 'Gas Stove 002', 100, 9, 1, 1, 2, 1);
INSERT INTO public.product VALUES (3, '2024-11-07 22:07:06.4057', true, '2024-11-07 22:07:30.480955', 1000, 'Fry Pan 12 inch', 700, 100, 1, 1, 1, 1);


--
-- TOC entry 3550 (class 0 OID 21697)
-- Dependencies: 228
-- Data for Name: product_category; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.product_category VALUES (1, '2024-11-06 22:39:59.997544', true, '2024-11-06 22:39:59.997563', 'Gas Stove', 1, NULL);


--
-- TOC entry 3552 (class 0 OID 21978)
-- Dependencies: 230
-- Data for Name: purchase; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.purchase VALUES (1, '2024-11-07 01:31:21.69273', true, '2024-11-07 01:31:21.692827', '2024-11-06', 1525, 'Cookware Industries Ltd.', 1, NULL);
INSERT INTO public.purchase VALUES (2, '2024-11-07 01:32:56.125973', true, '2024-11-07 01:32:56.125992', '2024-11-06', 750, 'Cookware Industries Ltd.', 1, NULL);
INSERT INTO public.purchase VALUES (3, '2024-11-07 18:50:12.45163', true, '2024-11-07 18:50:12.451653', '2024-11-07', 700, 'XYZ Corp.', 1, NULL);
INSERT INTO public.purchase VALUES (4, '2024-11-07 18:52:30.420875', true, '2024-11-07 18:52:30.420902', '2024-11-07', 750, 'XYZ Corp.', 1, NULL);
INSERT INTO public.purchase VALUES (5, '2024-11-07 22:07:30.448486', true, '2024-11-07 22:07:30.448554', '2024-11-07', 60000, 'XYZ Corp.', 1, NULL);


--
-- TOC entry 3553 (class 0 OID 21984)
-- Dependencies: 231
-- Data for Name: purchase_purchased_products; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.purchase_purchased_products VALUES (1, 1);
INSERT INTO public.purchase_purchased_products VALUES (1, 2);
INSERT INTO public.purchase_purchased_products VALUES (2, 3);
INSERT INTO public.purchase_purchased_products VALUES (3, 4);
INSERT INTO public.purchase_purchased_products VALUES (4, 5);
INSERT INTO public.purchase_purchased_products VALUES (5, 6);


--
-- TOC entry 3555 (class 0 OID 21990)
-- Dependencies: 233
-- Data for Name: purchased_product; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.purchased_product VALUES (1, '2024-11-07 01:31:21.708593', true, '2024-11-07 01:31:21.708625', 75, 15, 1, NULL, 2, NULL);
INSERT INTO public.purchased_product VALUES (2, '2024-11-07 01:31:21.710666', true, '2024-11-07 01:31:21.710689', 80, 5, 1, NULL, 1, NULL);
INSERT INTO public.purchased_product VALUES (3, '2024-11-07 01:32:56.127408', true, '2024-11-07 01:32:56.127422', 75, 10, 1, NULL, 2, NULL);
INSERT INTO public.purchased_product VALUES (4, '2024-11-07 18:50:12.455685', true, '2024-11-07 18:50:12.455719', 70, 10, 1, NULL, 2, NULL);
INSERT INTO public.purchased_product VALUES (5, '2024-11-07 18:52:30.42257', true, '2024-11-07 18:52:30.422584', 75, 10, 1, NULL, 1, NULL);
INSERT INTO public.purchased_product VALUES (6, '2024-11-07 22:07:30.455565', true, '2024-11-07 22:07:30.455616', 600, 100, 1, NULL, 3, NULL);


--
-- TOC entry 3540 (class 0 OID 21580)
-- Dependencies: 218
-- Data for Name: refresh_token; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.refresh_token VALUES (1, '2024-11-08 20:57:06.952112+06', 'c4d46e46-4141-43de-b42e-b00dc9404e43', 1);


--
-- TOC entry 3542 (class 0 OID 21587)
-- Dependencies: 220
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.roles VALUES (2, '2024-11-06 14:14:27.168226', true, '2024-11-06 14:14:27.192638', 'OFFICER', NULL, NULL);
INSERT INTO public.roles VALUES (3, '2024-11-06 14:14:27.170354', true, '2024-11-06 14:14:27.193612', 'MAINTAINER', NULL, NULL);
INSERT INTO public.roles VALUES (4, '2024-11-06 14:14:27.172335', true, '2024-11-06 14:14:27.194361', 'INV_SYS_ADMIN', NULL, NULL);
INSERT INTO public.roles VALUES (1, '2024-11-06 14:14:27.152805', true, '2024-11-06 15:47:32.45784', 'ADMIN', NULL, 1);


--
-- TOC entry 3557 (class 0 OID 21997)
-- Dependencies: 235
-- Data for Name: sale; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.sale VALUES (4, '2024-11-07 02:18:36.835419', true, '2024-11-07 02:18:36.835494', '38-40, Road 09, Block D, Mirpur 12, Dhaka-1216', 'Arman Joy', '01738014685', false, 100, 5, false, '2024-11-05', 730, 1, NULL, 600);
INSERT INTO public.sale VALUES (5, '2024-11-07 21:12:17.556857', true, '2024-11-07 21:12:17.556881', 'Pallabi, Mirpur-12', 'Mr. Arman', '01738014685', false, 120, 10, false, '2024-11-07', 340, 1, NULL, 200);
INSERT INTO public.sale VALUES (6, '2024-11-07 22:03:04.793378', true, '2024-11-07 22:03:04.793448', 'Mirpur-12', 'Mr. Reza', '01738014685', false, 100, 10, false, '2024-11-07', 640, 1, NULL, 600);


--
-- TOC entry 3558 (class 0 OID 22003)
-- Dependencies: 236
-- Data for Name: sale_sold_products; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.sale_sold_products VALUES (4, 7);
INSERT INTO public.sale_sold_products VALUES (4, 8);
INSERT INTO public.sale_sold_products VALUES (5, 9);
INSERT INTO public.sale_sold_products VALUES (6, 10);


--
-- TOC entry 3560 (class 0 OID 22009)
-- Dependencies: 238
-- Data for Name: sold_product; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.sold_product VALUES (7, '2024-11-07 02:18:36.845421', true, '2024-11-07 02:18:36.845444', NULL, 1, 100, 1, NULL, 1, NULL);
INSERT INTO public.sold_product VALUES (8, '2024-11-07 02:18:36.846718', true, '2024-11-07 02:18:36.846737', NULL, 5, 100, 1, NULL, 2, NULL);
INSERT INTO public.sold_product VALUES (9, '2024-11-07 21:12:17.568973', true, '2024-11-07 21:12:17.568992', NULL, 2, 100, 1, NULL, 1, NULL);
INSERT INTO public.sold_product VALUES (10, '2024-11-07 22:03:04.815256', true, '2024-11-07 22:03:04.815297', NULL, 6, 100, 1, NULL, 2, NULL);


--
-- TOC entry 3544 (class 0 OID 21594)
-- Dependencies: 222
-- Data for Name: t_users; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.t_users VALUES (1, '2024-11-06 15:42:34.407079', true, '2024-11-06 15:42:34.407137', 'email2arjoy@gmail.com', 'Arman Joy', '$2a$10$4iYeMqD8vYgqcTD1TKWdHegYfkO5DtVZSnuzcw8qWifsS1uDzWJpm', 'armanjoy', NULL, NULL, 1);
INSERT INTO public.t_users VALUES (2, '2024-11-06 15:46:46.035227', true, '2024-11-06 15:47:32.46625', 'ra@gmail.com', 'Reza Ahmed', '$2a$10$7SNColAvMa5dCXp0714mmu1PjKOYt.OGw8Kcmh.UgGoJQoOb1QNlW', 'eg-0001', 1, 1, 1);


--
-- TOC entry 3582 (class 0 OID 0)
-- Dependencies: 223
-- Name: brand_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.brand_id_seq', 3, true);


--
-- TOC entry 3583 (class 0 OID 0)
-- Dependencies: 211
-- Name: employee_designation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.employee_designation_id_seq', 1, true);


--
-- TOC entry 3584 (class 0 OID 0)
-- Dependencies: 209
-- Name: employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.employee_id_seq', 1, true);


--
-- TOC entry 3585 (class 0 OID 0)
-- Dependencies: 213
-- Name: login_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.login_history_id_seq', 29, true);


--
-- TOC entry 3586 (class 0 OID 0)
-- Dependencies: 215
-- Name: otp_entries_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.otp_entries_id_seq', 1, false);


--
-- TOC entry 3587 (class 0 OID 0)
-- Dependencies: 227
-- Name: product_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.product_category_id_seq', 1, true);


--
-- TOC entry 3588 (class 0 OID 0)
-- Dependencies: 225
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.product_id_seq', 3, true);


--
-- TOC entry 3589 (class 0 OID 0)
-- Dependencies: 229
-- Name: purchase_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.purchase_id_seq', 5, true);


--
-- TOC entry 3590 (class 0 OID 0)
-- Dependencies: 232
-- Name: purchased_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.purchased_product_id_seq', 6, true);


--
-- TOC entry 3591 (class 0 OID 0)
-- Dependencies: 217
-- Name: refresh_token_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.refresh_token_id_seq', 1, true);


--
-- TOC entry 3592 (class 0 OID 0)
-- Dependencies: 219
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.roles_id_seq', 4, true);


--
-- TOC entry 3593 (class 0 OID 0)
-- Dependencies: 234
-- Name: sale_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.sale_id_seq', 6, true);


--
-- TOC entry 3594 (class 0 OID 0)
-- Dependencies: 237
-- Name: sold_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.sold_product_id_seq', 10, true);


--
-- TOC entry 3595 (class 0 OID 0)
-- Dependencies: 221
-- Name: t_users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.t_users_id_seq', 2, true);


--
-- TOC entry 3326 (class 2606 OID 21688)
-- Name: brand brand_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.brand
    ADD CONSTRAINT brand_pkey PRIMARY KEY (id);


--
-- TOC entry 3301 (class 2606 OID 21560)
-- Name: employee_designation employee_designation_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employee_designation
    ADD CONSTRAINT employee_designation_pkey PRIMARY KEY (id);


--
-- TOC entry 3295 (class 2606 OID 21553)
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);


--
-- TOC entry 3306 (class 2606 OID 21569)
-- Name: login_history login_history_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.login_history
    ADD CONSTRAINT login_history_pkey PRIMARY KEY (id);


--
-- TOC entry 3308 (class 2606 OID 21578)
-- Name: otp_entries otp_entries_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.otp_entries
    ADD CONSTRAINT otp_entries_pkey PRIMARY KEY (id);


--
-- TOC entry 3334 (class 2606 OID 21702)
-- Name: product_category product_category_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT product_category_pkey PRIMARY KEY (id);


--
-- TOC entry 3330 (class 2606 OID 21695)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 3338 (class 2606 OID 21983)
-- Name: purchase purchase_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT purchase_pkey PRIMARY KEY (id);


--
-- TOC entry 3340 (class 2606 OID 21988)
-- Name: purchase_purchased_products purchase_purchased_products_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.purchase_purchased_products
    ADD CONSTRAINT purchase_purchased_products_pkey PRIMARY KEY (purchase_id, purchased_products_id);


--
-- TOC entry 3345 (class 2606 OID 21995)
-- Name: purchased_product purchased_product_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.purchased_product
    ADD CONSTRAINT purchased_product_pkey PRIMARY KEY (id);


--
-- TOC entry 3311 (class 2606 OID 21585)
-- Name: refresh_token refresh_token_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.refresh_token
    ADD CONSTRAINT refresh_token_pkey PRIMARY KEY (id);


--
-- TOC entry 3315 (class 2606 OID 21592)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 3347 (class 2606 OID 22002)
-- Name: sale sale_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sale
    ADD CONSTRAINT sale_pkey PRIMARY KEY (id);


--
-- TOC entry 3349 (class 2606 OID 22007)
-- Name: sale_sold_products sale_sold_products_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sale_sold_products
    ADD CONSTRAINT sale_sold_products_pkey PRIMARY KEY (sale_id, sold_products_id);


--
-- TOC entry 3354 (class 2606 OID 22014)
-- Name: sold_product sold_product_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sold_product
    ADD CONSTRAINT sold_product_pkey PRIMARY KEY (id);


--
-- TOC entry 3320 (class 2606 OID 21599)
-- Name: t_users t_users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.t_users
    ADD CONSTRAINT t_users_pkey PRIMARY KEY (id);


--
-- TOC entry 3322 (class 2606 OID 21614)
-- Name: t_users uk_1f8qpknpngd98342v0j2ceadc; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.t_users
    ADD CONSTRAINT uk_1f8qpknpngd98342v0j2ceadc UNIQUE (email);


--
-- TOC entry 3336 (class 2606 OID 21708)
-- Name: product_category uk_9qvug0bmpkmxkkx33q51m7do7; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT uk_9qvug0bmpkmxkkx33q51m7do7 UNIQUE (name);


--
-- TOC entry 3342 (class 2606 OID 22016)
-- Name: purchase_purchased_products uk_arfn0b6pdgdgdo8yswiu5ygn0; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.purchase_purchased_products
    ADD CONSTRAINT uk_arfn0b6pdgdgdo8yswiu5ygn0 UNIQUE (purchased_products_id);


--
-- TOC entry 3303 (class 2606 OID 21605)
-- Name: employee_designation uk_boi7uckaq92hyve8t8rfpfycm; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employee_designation
    ADD CONSTRAINT uk_boi7uckaq92hyve8t8rfpfycm UNIQUE (name);


--
-- TOC entry 3351 (class 2606 OID 22019)
-- Name: sale_sold_products uk_e8tcnbrnae3mblvwt7trme0lg; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sale_sold_products
    ADD CONSTRAINT uk_e8tcnbrnae3mblvwt7trme0lg UNIQUE (sold_products_id);


--
-- TOC entry 3313 (class 2606 OID 21609)
-- Name: refresh_token uk_f95ixxe7pa48ryn1awmh2evt7; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.refresh_token
    ADD CONSTRAINT uk_f95ixxe7pa48ryn1awmh2evt7 UNIQUE (user_id);


--
-- TOC entry 3332 (class 2606 OID 21706)
-- Name: product uk_jmivyxk9rmgysrmsqw15lqr5b; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT uk_jmivyxk9rmgysrmsqw15lqr5b UNIQUE (name);


--
-- TOC entry 3299 (class 2606 OID 21603)
-- Name: employee uk_mc5x07dj0uft9opsxchp0uwji; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT uk_mc5x07dj0uft9opsxchp0uwji UNIQUE (employee_id);


--
-- TOC entry 3317 (class 2606 OID 21611)
-- Name: roles uk_ofx66keruapi6vyqpv6f2or37; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT uk_ofx66keruapi6vyqpv6f2or37 UNIQUE (name);


--
-- TOC entry 3328 (class 2606 OID 21704)
-- Name: brand uk_rdxh7tq2xs66r485cc8dkxt77; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.brand
    ADD CONSTRAINT uk_rdxh7tq2xs66r485cc8dkxt77 UNIQUE (name);


--
-- TOC entry 3324 (class 2606 OID 21616)
-- Name: t_users uk_sp0e01od15gf4nu5ffu87qb9n; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.t_users
    ADD CONSTRAINT uk_sp0e01od15gf4nu5ffu87qb9n UNIQUE (username);


--
-- TOC entry 3296 (class 1259 OID 21601)
-- Name: in_employee_designation_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX in_employee_designation_id ON public.employee USING btree (designation_id);


--
-- TOC entry 3297 (class 1259 OID 21600)
-- Name: in_employee_user_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX in_employee_user_id ON public.employee USING btree (user_id);


--
-- TOC entry 3304 (class 1259 OID 21606)
-- Name: in_login_history_user_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX in_login_history_user_id ON public.login_history USING btree (login_by);


--
-- TOC entry 3343 (class 1259 OID 22017)
-- Name: in_purchased_product_purchase_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX in_purchased_product_purchase_id ON public.purchased_product USING btree (purchase_id);


--
-- TOC entry 3309 (class 1259 OID 21607)
-- Name: in_refresh_token_user_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX in_refresh_token_user_id ON public.refresh_token USING btree (user_id);


--
-- TOC entry 3352 (class 1259 OID 22020)
-- Name: in_sold_product_sale_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX in_sold_product_sale_id ON public.sold_product USING btree (sale_id);


--
-- TOC entry 3318 (class 1259 OID 21612)
-- Name: in_user_role_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX in_user_role_id ON public.t_users USING btree (role_id);


--
-- TOC entry 3374 (class 2606 OID 21739)
-- Name: product_category fk15vjbh2n6bkf2p35hvl2w26g3; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT fk15vjbh2n6bkf2p35hvl2w26g3 FOREIGN KEY (created_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3378 (class 2606 OID 22031)
-- Name: purchase_purchased_products fk22wm1wxbvfw0f1u45293h8nk2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.purchase_purchased_products
    ADD CONSTRAINT fk22wm1wxbvfw0f1u45293h8nk2 FOREIGN KEY (purchased_products_id) REFERENCES public.purchased_product(id);


--
-- TOC entry 3370 (class 2606 OID 21719)
-- Name: product fk36jhskfke2nx7dbgvwufguumd; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk36jhskfke2nx7dbgvwufguumd FOREIGN KEY (created_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3380 (class 2606 OID 22046)
-- Name: purchased_product fk3re80suq3qolnp4qi7lawq9lc; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.purchased_product
    ADD CONSTRAINT fk3re80suq3qolnp4qi7lawq9lc FOREIGN KEY (updated_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3355 (class 2606 OID 21622)
-- Name: employee fk4v0gapm11rxy8six2obblmqca; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fk4v0gapm11rxy8six2obblmqca FOREIGN KEY (updated_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3365 (class 2606 OID 21667)
-- Name: t_users fk5g63rryj2affr4vykqwf3e65h; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.t_users
    ADD CONSTRAINT fk5g63rryj2affr4vykqwf3e65h FOREIGN KEY (created_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3376 (class 2606 OID 22021)
-- Name: purchase fk6nojym5iac7vb8k839d0tt4vt; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT fk6nojym5iac7vb8k839d0tt4vt FOREIGN KEY (created_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3359 (class 2606 OID 21642)
-- Name: employee_designation fk8ewd78py33q90enw6jjeqvhm6; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employee_designation
    ADD CONSTRAINT fk8ewd78py33q90enw6jjeqvhm6 FOREIGN KEY (updated_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3377 (class 2606 OID 22026)
-- Name: purchase fk8ivxiu1qv9kwy7d22d9pahs5l; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT fk8ivxiu1qv9kwy7d22d9pahs5l FOREIGN KEY (updated_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3371 (class 2606 OID 21724)
-- Name: product fk96dms34b9q62t5hxxcn2upfit; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk96dms34b9q62t5hxxcn2upfit FOREIGN KEY (updated_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3381 (class 2606 OID 22041)
-- Name: purchased_product fk9vxoq7s59bk061ddkuywwvxqh; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.purchased_product
    ADD CONSTRAINT fk9vxoq7s59bk061ddkuywwvxqh FOREIGN KEY (created_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3372 (class 2606 OID 21729)
-- Name: product fk_brand_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_brand_id FOREIGN KEY (brand_id) REFERENCES public.brand(id);


--
-- TOC entry 3373 (class 2606 OID 21734)
-- Name: product fk_category_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES public.product_category(id);


--
-- TOC entry 3356 (class 2606 OID 21627)
-- Name: employee fk_employee_designation_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fk_employee_designation_id FOREIGN KEY (designation_id) REFERENCES public.employee_designation(id);


--
-- TOC entry 3357 (class 2606 OID 21632)
-- Name: employee fk_employee_user_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fk_employee_user_id FOREIGN KEY (user_id) REFERENCES public.t_users(id);


--
-- TOC entry 3361 (class 2606 OID 21647)
-- Name: login_history fk_login_history_user_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.login_history
    ADD CONSTRAINT fk_login_history_user_id FOREIGN KEY (login_by) REFERENCES public.t_users(id);


--
-- TOC entry 3382 (class 2606 OID 22051)
-- Name: purchased_product fk_purchase_product_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.purchased_product
    ADD CONSTRAINT fk_purchase_product_id FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3383 (class 2606 OID 22056)
-- Name: purchased_product fk_purchased_product_purchase_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.purchased_product
    ADD CONSTRAINT fk_purchased_product_purchase_id FOREIGN KEY (purchase_id) REFERENCES public.purchase(id);


--
-- TOC entry 3362 (class 2606 OID 21652)
-- Name: refresh_token fk_refresh_token_user_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.refresh_token
    ADD CONSTRAINT fk_refresh_token_user_id FOREIGN KEY (user_id) REFERENCES public.t_users(id);


--
-- TOC entry 3388 (class 2606 OID 22091)
-- Name: sold_product fk_sold_product_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sold_product
    ADD CONSTRAINT fk_sold_product_id FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3389 (class 2606 OID 22096)
-- Name: sold_product fk_sold_product_sale_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sold_product
    ADD CONSTRAINT fk_sold_product_sale_id FOREIGN KEY (sale_id) REFERENCES public.sale(id);


--
-- TOC entry 3366 (class 2606 OID 21677)
-- Name: t_users fk_user_role_id; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.t_users
    ADD CONSTRAINT fk_user_role_id FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- TOC entry 3368 (class 2606 OID 21714)
-- Name: brand fkar4pxhk7f60042shomt2e5lq3; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.brand
    ADD CONSTRAINT fkar4pxhk7f60042shomt2e5lq3 FOREIGN KEY (updated_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3363 (class 2606 OID 21657)
-- Name: roles fkht5d8xyc7xh28g3lkkaj9slyr; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT fkht5d8xyc7xh28g3lkkaj9slyr FOREIGN KEY (created_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3375 (class 2606 OID 21744)
-- Name: product_category fkijdkuqdkwq18eg8ya2kauw2qs; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT fkijdkuqdkwq18eg8ya2kauw2qs FOREIGN KEY (updated_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3369 (class 2606 OID 21709)
-- Name: brand fkj1w87px3lthd55sb3c8p7lapl; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.brand
    ADD CONSTRAINT fkj1w87px3lthd55sb3c8p7lapl FOREIGN KEY (created_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3379 (class 2606 OID 22036)
-- Name: purchase_purchased_products fkka4q8s0861hcalhw0xbg5ov2n; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.purchase_purchased_products
    ADD CONSTRAINT fkka4q8s0861hcalhw0xbg5ov2n FOREIGN KEY (purchase_id) REFERENCES public.purchase(id);


--
-- TOC entry 3384 (class 2606 OID 22061)
-- Name: sale fklqdq4aqohupsixehjpb1p2ukt; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sale
    ADD CONSTRAINT fklqdq4aqohupsixehjpb1p2ukt FOREIGN KEY (created_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3360 (class 2606 OID 21637)
-- Name: employee_designation fkm2o74rvmiqwxulkm43jlonntp; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employee_designation
    ADD CONSTRAINT fkm2o74rvmiqwxulkm43jlonntp FOREIGN KEY (created_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3367 (class 2606 OID 21672)
-- Name: t_users fkn3lbaaexlsdnkoj0t36jdykuc; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.t_users
    ADD CONSTRAINT fkn3lbaaexlsdnkoj0t36jdykuc FOREIGN KEY (updated_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3390 (class 2606 OID 22081)
-- Name: sold_product fknb3dulabgjain31s8yegp6w3l; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sold_product
    ADD CONSTRAINT fknb3dulabgjain31s8yegp6w3l FOREIGN KEY (created_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3358 (class 2606 OID 21617)
-- Name: employee fko8owmegj7yg2p4dka2oqqrdk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fko8owmegj7yg2p4dka2oqqrdk FOREIGN KEY (created_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3386 (class 2606 OID 22076)
-- Name: sale_sold_products fkojrreq4i8y2d3kt0bfa6yet5w; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sale_sold_products
    ADD CONSTRAINT fkojrreq4i8y2d3kt0bfa6yet5w FOREIGN KEY (sale_id) REFERENCES public.sale(id);


--
-- TOC entry 3385 (class 2606 OID 22066)
-- Name: sale fkokk2siartfdv9ikqinkemkgv8; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sale
    ADD CONSTRAINT fkokk2siartfdv9ikqinkemkgv8 FOREIGN KEY (updated_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3364 (class 2606 OID 21662)
-- Name: roles fkr2noasp9t8s7ovdqfxgpqk9b6; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT fkr2noasp9t8s7ovdqfxgpqk9b6 FOREIGN KEY (updated_by_id) REFERENCES public.t_users(id);


--
-- TOC entry 3387 (class 2606 OID 22071)
-- Name: sale_sold_products fkrcgng4v9x8ip2nrt2bshu9u4i; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sale_sold_products
    ADD CONSTRAINT fkrcgng4v9x8ip2nrt2bshu9u4i FOREIGN KEY (sold_products_id) REFERENCES public.sold_product(id);


--
-- TOC entry 3391 (class 2606 OID 22086)
-- Name: sold_product fkrjcfv6lyyui8y3gu02caxbpmu; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sold_product
    ADD CONSTRAINT fkrjcfv6lyyui8y3gu02caxbpmu FOREIGN KEY (updated_by_id) REFERENCES public.t_users(id);


-- Completed on 2024-11-07 22:10:16 +06

--
-- PostgreSQL database dump complete
--

