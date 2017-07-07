CREATE INDEX contestants_bs_idx ON contestants USING btree (bachelor_start_date);
CREATE INDEX contestants_be_idx ON contestants USING btree (bachelor_end_date);
CREATE INDEX contests_start_datetime_idx ON contests USING btree (start_datetime);
CREATE INDEX contests_end_datetime_idx ON contests USING btree (end_datetime);